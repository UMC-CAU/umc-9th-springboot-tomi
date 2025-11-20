package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.entity.ReviewImage;

public class ReviewResConvertor {

    public static ReviewResDTO.searchDTO toSearchDTO(Review review) {
        return new ReviewResDTO.searchDTO(
                review.getId(),
                review.getContent(),
                review.getScore(),
                review.getMember().getId(),
                review.getMember().getName(),
                review.getStore().getId(),
                review.getStore().getName()
        );
    }

    public static ReviewResDTO.createDTO toCreateDTO(Review review) {
        return new ReviewResDTO.createDTO(
                review.getId(),
                review.getStore().getId(),
                review.getMember().getId(),
                review.getContent(),
                review.getScore()
        );
    }
}
