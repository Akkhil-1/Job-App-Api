package com.springboot.JobApp.review;

import java.util.List;

public interface ReviewService {

    List<Review> getAllReviews(long companyId);

    boolean createReview(long companyId , Review review);

    Review getReviewById(long companyId , long reviewId);

    boolean updateReview(long companyId , long reviewId , Review updateReview);

    boolean deleteReview(long companyId , long reviewId);
}
