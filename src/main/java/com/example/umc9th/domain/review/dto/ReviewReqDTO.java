package com.example.umc9th.domain.review.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
@AllArgsConstructor
public class ReviewReqDTO {

    public record searchDTO(
            Long memberId,
            Long storeId,
            Integer score

    ){}

    public record createDTO(@NotNull String content, @Range(min = 0, max = 5) float score
    ){}
}
