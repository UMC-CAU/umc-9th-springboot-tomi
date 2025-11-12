package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.ReviewResponseDTO;
import com.example.umc9th.domain.review.dto.ReviewSearchDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.service.ReviewService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/members/{memberId}/reviews")
    public ResponseEntity<ApiResponse<List<ReviewResponseDTO>>> searchMyReview(@PathVariable("memberId") Long memberId,
                                                      @RequestParam(required = false) Long storeId,
                                                      @RequestParam(required = false) Integer score) {

        ReviewSearchDTO reviewSearchDTO = new ReviewSearchDTO(memberId, storeId, score);
        List<ReviewResponseDTO> reviews = reviewService.searchMyReview(reviewSearchDTO);
        return ResponseEntity.ok(ApiResponse.onSuccess(GeneralSuccessCode.OK, reviews));
    }
}
