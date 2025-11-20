package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.dto.ReviewResponseDTO;
import com.example.umc9th.domain.review.dto.ReviewSearchDTO;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public List<ReviewResponseDTO> searchMyReview(ReviewSearchDTO reviewSearchDTO) {
        List<Review> reviews = reviewRepository.searchMyReview(reviewSearchDTO);
        return reviews.stream()
                .map(r -> new ReviewResponseDTO(
                        r.getId(),
                        r.getContent(),
                        r.getScore(),
                        r.getStore().getId(),
                        r.getStore().getName(),
                        r.getMember().getId(),
                        r.getMember().getName()
                ))
                .toList();
    }
}
