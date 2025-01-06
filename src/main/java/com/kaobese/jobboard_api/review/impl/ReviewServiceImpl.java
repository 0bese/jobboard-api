package com.kaobese.jobboard_api.review.impl;

import com.kaobese.jobboard_api.company.Company;
import com.kaobese.jobboard_api.company.CompanyRepository;
import com.kaobese.jobboard_api.review.Review;
import com.kaobese.jobboard_api.review.ReviewRepository;
import com.kaobese.jobboard_api.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    ReviewRepository reviewRepo;
    CompanyRepository companyRepo;

    public ReviewServiceImpl(ReviewRepository reviewRepo, CompanyRepository companyRepo) {
        this.reviewRepo = reviewRepo;
        this.companyRepo = companyRepo;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepo.findByCompanyId(companyId);
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        try{
            Company company = companyRepo.getCompanyById(companyId);
            if(company != null){
                review.setCompany(company);
                reviewRepo.save(review);
                return true;
            }
            return  false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Review getReviewById(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepo.findByCompanyId(companyId);
        return reviews.stream().filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review review) {
        try{
            Company company = companyRepo.findById(companyId).orElseThrow();
            Review existingReview = company.getReviews().stream()
                    .filter(r -> r.getId().equals(reviewId))
                    .findFirst()
                    .orElseThrow();

            existingReview.setTitle(review.getTitle());
            existingReview.setDescription(review.getDescription());
            existingReview.setRating(review.getRating());

            companyRepo.save(company);
            return true;


        } catch (Exception e) {
            return false;
        }
    }

    @Override

    public boolean deleteReview(Long companyId, Long reviewId) {
        try {
            Company company = companyRepo.findById(companyId)
                    .orElseThrow();
            Review review = reviewRepo.findById(reviewId)
                    .orElseThrow();

            if(!company.getReviews().contains(review)){
                throw new Exception();
            }

            company.getReviews().remove(review);
            companyRepo.save(company);
            reviewRepo.delete(review);

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
