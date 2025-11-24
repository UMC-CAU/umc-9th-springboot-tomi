package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.review.dto.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.stream.Collectors;

public class ReviewConverter {

    public static ReviewReqDTO.searchDTO toSearchReqDTO(Long memberId, Long storeId, Integer score) {
        return new ReviewReqDTO.searchDTO(memberId, storeId, score);
    }

    public static ReviewResDTO.searchDTO toSearchResDTO(Review review) {
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

    // result -> DTO
    public static ReviewResDTO.ReviewPreViewListDTO toReviewPreviewListDTO(
            Page<Review> result
    ){
        return ReviewResDTO.ReviewPreViewListDTO.builder()
                .reviewList(result.getContent().stream()
                        .map(ReviewConverter::toReviewPreviewDTO)
                        .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static ReviewResDTO.ReviewPreViewDTO toReviewPreviewDTO(
            Review review
    ){
        return ReviewResDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getScore())
                .body(review.getContent())
                .createdAt(LocalDate.from(review.getCreatedAt()))
                .build();
    }

    // Page<Review> → MyReviewListDTO 변환
    public static ReviewResDTO.MyReviewListDTO toMyReviewListDTO(Page<Review> result) {
        return ReviewResDTO.MyReviewListDTO.builder()
                .reviewList(result.getContent().stream()
                        .map(ReviewConverter::toMyReviewDTO)
                        .toList()
                )
                .listSize(result.getNumberOfElements())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();


    }

    // Review → MyReviewDTO 변환
    public static ReviewResDTO.MyReviewDTO toMyReviewDTO(Review review) {
        return ReviewResDTO.MyReviewDTO.builder()
                .reviewId(review.getId())
                .storeName(review.getStore().getName())
                .score(review.getScore())
                .body(review.getContent())
                .createdAt(review.getCreatedAt().toLocalDate())
                .build();
    }

}
