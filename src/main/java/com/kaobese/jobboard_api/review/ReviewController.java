package com.kaobese.jobboard_api.review;

import com.kaobese.jobboard_api.company.Company;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {

    ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId){
        return new ResponseEntity<>(reviewService.getAllReviews(companyId),HttpStatus.OK);
    }

    @PostMapping("/reviews")
    ResponseEntity<String> addReview(@PathVariable Long companyId, @RequestBody Review review){
        boolean added = reviewService.addReview(companyId, review);
        if(added)
            return new ResponseEntity<>("Review created successfully", HttpStatus.OK);
        return new ResponseEntity<>("Error creating review", HttpStatus.NOT_FOUND);
    }

    @GetMapping("reviews/{reviewId}")
    ResponseEntity<Review> getReviewById(@PathVariable Long companyId, @PathVariable Long reviewId){
        Review found = reviewService.getReviewById(companyId, reviewId);
        if(found != null)
            return new ResponseEntity<>(found, HttpStatus.OK);
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }

    @PutMapping("reviews/{reviewId}")
    ResponseEntity<String> updateReview(@PathVariable Long companyId, @PathVariable Long reviewId, @RequestBody Review review){
        boolean isUpdated = reviewService.updateReview(companyId, reviewId, review);
        if(isUpdated)
            return new ResponseEntity<>("Review updated successfully", HttpStatus.OK);
        return new ResponseEntity<>("Error updating review", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("reviews/{reviewId}")
    ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long reviewId){
        boolean isDeleted = reviewService.deleteReview(companyId, reviewId);
        if(isDeleted)
            return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
        return new ResponseEntity<>("Error deleting review", HttpStatus.NOT_FOUND);
    }

}
/*
 * POST /companies/{companyId}/reviews
 * GET /companies/{companyId}/reviews
 * GET /companies/{companyId}/reviews/{reviewId}
 * PUT /companies/{companyId}/reviews/{reviewId}
 * DELETE /companies/{companyId}/reviews/{reviewId}
 */