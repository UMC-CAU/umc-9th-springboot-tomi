package com.example.umc9th.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewSearchDTO {
    private Long memberId;
    private Long storeId;
    private Integer score;
}
