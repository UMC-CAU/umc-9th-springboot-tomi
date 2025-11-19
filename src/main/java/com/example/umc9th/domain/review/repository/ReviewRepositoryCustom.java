package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;

import java.util.List;

public interface ReviewRepositoryCustom {

    public List<Review> searchMyReview(Long memberId, Long storeId, Integer score);
}