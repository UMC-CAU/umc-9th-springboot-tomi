package com.example.umc9th.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewResponseDTO {

    private Long reviewId;
    private String content;
    private Float score;

    private Long memberId;
    private String memberName;

    private Long storeId;
    private String storeName;
}
