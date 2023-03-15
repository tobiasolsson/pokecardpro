package com.pokecardpro.service;

import com.pokecardpro.models.Reviews;
import com.pokecardpro.models.User;
import com.pokecardpro.repository.ReviewRepository;
import com.pokecardpro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    UserRepository userRepository;

    public Reviews createReview (Reviews reviews) {
        String commentReceiverId = Integer.toString(reviews.getCommentReceiver().getId());
        String commentOwnerId = Integer.toString(reviews.getCommentOwner().getId());
        User commentReceiver = userRepository.findById(commentReceiverId)
                                             .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id " + commentReceiverId));
        User commentOwner = userRepository.findById(commentOwnerId)
                                          .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id " + commentOwnerId));
        reviews.setCommentReceiver(commentReceiver);
        reviews.setCommentOwner(commentOwner);

        return reviewRepository.save(reviews);
    }

    public Reviews getReviewById (String id) {
        return reviewRepository.findById(id).get();

    }

    public List <Reviews> getAllReviews() {
        return reviewRepository.findAll();
    }


}
