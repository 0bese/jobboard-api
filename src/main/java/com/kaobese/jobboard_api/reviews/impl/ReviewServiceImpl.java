package com.kaobese.jobboard_api.reviews.impl;

import com.kaobese.jobboard_api.company.Company;
import com.kaobese.jobboard_api.company.CompanyRepository;
import com.kaobese.jobboard_api.reviews.Review;
import com.kaobese.jobboard_api.reviews.ReviewRepository;
import com.kaobese.jobboard_api.reviews.ReviewService;
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
    public Review getReviewById(Long reviewId) {
        return reviewRepo.findById(reviewId).orElse(null);
    }

    @Override
    public boolean updateReviewById() {
        return false;
    }

    @Override
    public boolean deleteReviewById() {
        return false;
    }
}
