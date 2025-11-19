package com.example.umc9th.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewResDTO {

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
}
