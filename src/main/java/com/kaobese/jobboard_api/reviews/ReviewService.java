package com.kaobese.jobboard_api.reviews;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface ReviewService {
    List<Review> getAllReviews(Long companyId);
    boolean addReview(Long companyId, Review review);
    Review getReviewById(Long id);
    boolean updateReviewById();
    boolean deleteReviewById();
}
