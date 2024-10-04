package com.jobapp.Review.MS.Controllers;

import com.jobapp.Review.MS.MessageQueue.ReviewMessageProducer;
import com.jobapp.Review.MS.Models.Review;
import com.jobapp.Review.MS.Services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController
{
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewMessageProducer messageProducer;

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviewsByCompany(@RequestParam Long companyId)
    {
        return new ResponseEntity<>(reviewService.findAll(companyId), HttpStatus.OK);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long reviewId)
    {
        Review review = reviewService.getReviewById(reviewId);
        return new ResponseEntity<>(review,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addReview(@RequestParam Long companyId,
                                            @RequestBody Review review)
    {
        boolean saved = reviewService.createReview(companyId,review);
        if(saved)
        {
            messageProducer.sendMessage(review );
            return new ResponseEntity<>("Review Added Successfully",HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId,
                                               @RequestBody Review review)
    {
        boolean updated = reviewService.updateReviewById(reviewId, review);
        if(updated)
        {
            return new ResponseEntity<>("Review Updated Successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId)
    {
        boolean deleted = reviewService.deleteReview(reviewId);
        if(deleted)
        {
            return new ResponseEntity<>("Review Deleted Successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/averageRating")
    public Double getAverageOfReviews(@RequestParam Long companyId)
    {
        List<Review> reviews = reviewService.findAll(companyId);
        return reviews.stream().mapToDouble(Review::getRating).average().orElse(0.0);
    }
}
