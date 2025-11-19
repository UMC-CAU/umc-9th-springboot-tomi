package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.domain.review.dto.ReviewReqDTO;
import com.example.umc9th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc9th.domain.review.service.ReviewService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    //특정 멤버 리뷰리스트 조회 (read)
    @GetMapping("/members/{memberId}/reviews")
    public ApiResponse<List<ReviewResDTO.searchDTO>> searchMyReview(@PathVariable("memberId") Long memberId,
                                                                                    @RequestParam(required = false) Long storeId,
                                                                                    @RequestParam(required = false) Integer score) {

        ReviewReqDTO.searchDTO reviewSearchDTO = new ReviewReqDTO.searchDTO(memberId, storeId, score);
        List<ReviewResDTO.searchDTO> reviews = reviewService.searchMyReview(reviewSearchDTO);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, reviews);
    }

    //특정 가게에 리뷰 적기 (create)
    @PostMapping("stores/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.createDTO> createReview(
            @PathVariable Long storeId,
            @Valid @RequestBody ReviewReqDTO.createDTO request,
            Long memberId // TODO : 우선 임시로 memberId 받음
    ) {

        return ApiResponse.onSuccess(ReviewSuccessCode.CREATED, reviewService.createReview(storeId, memberId, request));
    }

}
