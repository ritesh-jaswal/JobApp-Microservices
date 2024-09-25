package com.jobapp.Review.MS.Services;


import com.jobapp.Review.MS.Models.Review;

import java.util.List;

public interface ReviewService
{
    List<Review> findAll(Long companyId);
    boolean createReview(Long companyId,Review review);
    Review getReviewById(Long reviewId);
    boolean deleteReview(Long reviewId);
    boolean updateReviewById(Long reviewId, Review review);
}
