package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.domain.review.dto.ReviewReqDTO;
import com.example.umc9th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc9th.domain.review.service.ReviewService;
import com.example.umc9th.global.annotation.ValidPage;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
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

    //ch9 특정 가게의 리뷰 목록 조회
    @Operation(
            summary = "가게의 리뷰 목록 조회 API By 마크 (개발 중)",
            description = "특정 가게의 리뷰를 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @GetMapping("/reviews")
    public ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getReviews(
            @RequestParam String storeName,
            @RequestParam(defaultValue = "1") Integer page
    ){

        ReviewSuccessCode code = ReviewSuccessCode.FOUND;
        return ApiResponse.onSuccess(code, reviewService.findReviews(storeName, page - 1));
    }

    //ch9 내가 작성한 리뷰 조회
    @Operation(
            summary = "내가 작성한 리뷰 목록 조회 API",
            description = "특정 가게의 리뷰를 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @GetMapping("/reviews/my")
    public ApiResponse<ReviewResDTO.MyReviewListDTO> getMyReviews(
            @RequestParam Long memberId,  //TODO 우선 임시로
            @ValidPage @RequestParam(defaultValue = "1") Integer page
    ){
        return ApiResponse.onSuccess(ReviewSuccessCode.FOUND, reviewService.findMyReviews(memberId, page - 1));
    }
}
