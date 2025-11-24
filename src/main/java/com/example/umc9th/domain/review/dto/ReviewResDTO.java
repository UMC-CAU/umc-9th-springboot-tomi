package com.example.umc9th.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class ReviewResDTO {

    //특정 멤버의 리뷰 조회
    public record searchDTO(
            Long reviewId,
            String content,
            Float score,
            Long memberId,
            String memberName,
            Long storeId,
            String storeName
    ){}

    public record createDTO(
            Long reviewId,
            Long storeId,
            Long memberId,
            String content,
            float score //TODO : 사진 기능은 언젠가 추가 ㅎ.
    ) { }

    //특정 가게 리뷰들
    @Builder
    public record ReviewPreViewListDTO(
            List<ReviewPreViewDTO> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    //특정 가게 리뷰
    @Builder
    public record ReviewPreViewDTO(
            String ownerNickname,
            Float score,
            String body,
            LocalDate createdAt
    ){}


    @Builder
    public record MyReviewListDTO(
            List<MyReviewDTO> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    @Builder
    public record MyReviewDTO(
            Long reviewId,
            String storeName,
            Float score,
            String body,
            LocalDate createdAt
    ){}

}
