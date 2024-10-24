package com.springboot.JobApp.review;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {

    private ReviewService reviewService;
    public ReviewController(ReviewService reviewService)
    {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable long companyId)
    {
        return new ResponseEntity<>(reviewService.getAllReviews(companyId) , HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@PathVariable long companyId , @RequestBody Review review){
        boolean isReviewSaved = reviewService.createReview(companyId , review);
        if(isReviewSaved)
        {
            return new ResponseEntity<>("Review Added!" , HttpStatus.CREATED);
        }

        return new ResponseEntity<>("Review not saved" , HttpStatus.NOT_FOUND);
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReviewWithId(@PathVariable long companyId , @PathVariable long reviewId)
    {
        Review review = reviewService.getReviewById(companyId , reviewId);
        if(review != null)
        {
            return  new ResponseEntity<>(review , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable long companyId , @PathVariable long reviewId
                                                 , @RequestBody Review updateReview)
    {
        boolean isUpdated = reviewService.updateReview(companyId , reviewId , updateReview);
        if(isUpdated)
        {
            return new ResponseEntity<>("Review Updated Successfully" , HttpStatus.OK);
        }
        return new ResponseEntity<>("Review not found!" , HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable long companyId , @PathVariable long reviewId)
    {
        boolean deletedReview = reviewService.deleteReview(companyId , reviewId);
        if(deletedReview)
        {
            return new ResponseEntity<>("Review Deleted " , HttpStatus.OK);
        }
        return new ResponseEntity<>("Review not Found" , HttpStatus.NOT_FOUND);
    }
}
