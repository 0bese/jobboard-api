package com.kaobese.jobboard_api.reviews;

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
    ResponseEntity<String> createReview(@PathVariable Long companyId, @RequestBody Review review){
        boolean added = reviewService.addReview(companyId, review);
        if(added)
            return new ResponseEntity<>("Review created successfully", HttpStatus.OK);
        return new ResponseEntity<>("Something went wrong while creating review", HttpStatus.NOT_FOUND);
    }

}
/*
 * POST /companies/
 * GET /companies
 * GET /companies/{id}
 * PUT /companies/{id}
 * DELETE /companies/{id}
 */