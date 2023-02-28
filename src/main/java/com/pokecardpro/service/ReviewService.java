package com.pokecardpro.service;

import com.pokecardpro.models.Reviews;
import com.pokecardpro.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    ReviewRepository reviewRepository;

    public Reviews createReview (Reviews reviews) {
        return reviewRepository.save(reviews);
    }

    public Reviews getReviewById (String id) {
        return reviewRepository.findById(id).get();

    }

    public List <Reviews> getAllReviews() {
        return reviewRepository.findAll();
    }


}
