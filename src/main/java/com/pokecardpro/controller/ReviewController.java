package com.pokecardpro.controller;

import com.pokecardpro.models.Reviews;
import com.pokecardpro.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @PostMapping("createReview")
    public Reviews createReview(@RequestBody Reviews reviews) {
        return reviewService.createReview(reviews);
    }

    @GetMapping("getReview/{id}")
    public Reviews getReview(@PathVariable String id) {
        return reviewService.getReviewById(id);
    }

    @GetMapping("getAllReviews")
    public List <Reviews> getAllReviews () {
        return reviewService.getAllReviews();
    }


}
