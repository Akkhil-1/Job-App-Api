package com.springboot.JobApp.review.impl;
import com.springboot.JobApp.company.Company;
import com.springboot.JobApp.company.CompanyService;
import com.springboot.JobApp.review.Review;
import com.springboot.JobApp.review.ReviewRepository;
import com.springboot.JobApp.review.ReviewService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;
    private CompanyService companyService;

    private long nextId = 1L;

    public ReviewServiceImpl(ReviewRepository reviewRepository , CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean createReview(long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if(company != null)
        {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReviewById(long companyId, long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        if(reviews != null)
        {
            for(Review review : reviews)
            {
                if(review.getId() == reviewId)
                {
                    return  review;
                }
            }
        }
        return null;
    }

    @Override
    public boolean updateReview(long companyId, long reviewId, Review updateReview) {
        Review review = getReviewById(companyId , reviewId);
        if(review != null){
            review.setTitle(updateReview.getTitle());
            review.setDescription(updateReview.getDescription());
            review.setRating(updateReview.getRating());
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReview(long companyId, long reviewId) {
        if(companyService.getCompanyById(companyId) != null && reviewRepository.existsById(reviewId))
        {
            Review review = reviewRepository.findById(reviewId).orElse(null);
            Company company = null;
            if (review != null) {
                company = review.getCompany();
            }
            if (company != null) {
                company.getReviews().remove(review);
            }
            assert review != null;
            review.setCompany(null);
            companyService.updateCompany(companyId , company);
            reviewRepository.deleteById(reviewId);
            return true;
        }
        return false;
    }

}


