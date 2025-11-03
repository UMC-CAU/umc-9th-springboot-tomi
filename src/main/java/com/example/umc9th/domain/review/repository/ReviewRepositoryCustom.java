package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.dto.ReviewSearchDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.querydsl.core.types.Predicate;

import java.util.List;

public interface ReviewRepositoryCustom {

    public List<Review> searchMyReview(ReviewSearchDTO reviewSearchDTO);
}
