package com.jobapp.Review.MS.ServiceImpls;


import com.jobapp.Review.MS.Models.Review;
import com.jobapp.Review.MS.Repositories.ReviewRepo;
import com.jobapp.Review.MS.Services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService
{
    @Autowired
    private ReviewRepo reviewRepo;

    @Override
    public List<Review> findAll(Long companyId)
    {
        List<Review> reviews = reviewRepo.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean createReview(Long companyId,Review review)
    {
        if(companyId != null && review != null)
        {
            review.setCompanyId(companyId);
            reviewRepo.save(review);

            return true;
        }
        return false;
    }

    @Override
    public Review getReviewById(Long reviewId)
    {
       return reviewRepo.findById(reviewId).orElse(null);
    }

    @Override
    public boolean deleteReview(Long reviewId)
    {
        Review review = reviewRepo.findById(reviewId).orElse(null);
        if(review!=null)
        {
            reviewRepo.delete(review);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateReviewById(Long reviewId, Review review)
    {
        Review rev = reviewRepo.findById(reviewId).orElse(null);
        if(rev!=null)
        {
            review.setTitle(rev.getTitle());
            review.setDescription(rev.getDescription());
            review.setRating(rev.getRating());
            review.setCompanyId(rev.getCompanyId());
            reviewRepo.save(review);
            return true;
        }
        return false;
    }
}
