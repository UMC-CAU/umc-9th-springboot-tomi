package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.exception.MemberException;
import com.example.umc9th.domain.member.exception.code.MemberErrorCode;
import com.example.umc9th.domain.member.repository.MemberRepository;

import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.domain.review.dto.ReviewReqDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.exception.ReviewException;
import com.example.umc9th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.exception.StoreException;
import com.example.umc9th.domain.store.exception.code.StoreErrorCode;
import com.example.umc9th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    //특정멤버 리뷰 read
    public List<ReviewResDTO.searchDTO> searchMyReview(ReviewReqDTO.searchDTO reviewSearchDTO) {

        Long memberId = reviewSearchDTO.memberId();
        Long storeId = reviewSearchDTO.storeId();
        Integer score = reviewSearchDTO.score();
        List<Review> reviews = reviewRepository.searchMyReview(memberId, storeId, score);

        return reviews.stream()
                .map(r -> new ReviewResDTO.searchDTO(
                        r.getId(),
                        r.getContent(),
                        r.getScore(),
                        r.getStore().getId(),
                        r.getStore().getName(),
                        r.getMember().getId(),
                        r.getMember().getName()
                ))
                .toList();
    }

    //특정 가게에 리뷰 작성
    public ReviewResDTO.createDTO createReview(Long storeId, Long memberId, ReviewReqDTO.createDTO req
    ) {

        if (req.score() < 0 || req.score() > 5) {
            throw new ReviewException(ReviewErrorCode.INVALID_SCORE);
        }

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        Review review = Review.builder()
                .store(store)
                .member(member)
                .score(req.score())
                .content(req.content())
                .build();


        Review saved = reviewRepository.save(review);

        return ReviewConverter.toCreateDTO(saved);
    }

    //특정 가게 리뷰 리스트
    public ReviewResDTO.ReviewPreViewListDTO findReviews(String storeName,
                                                        Integer page){
        Store store = storeRepository.findByName(storeName)
                //    - 없으면 예외 터뜨린다
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        //- 가게에 맞는 리뷰를 가져온다 (Offset 페이징)
        PageRequest pageRequest = PageRequest.of(page, 5);
        Page<Review> result = reviewRepository.findAllByStore(store, pageRequest);

        //- 결과를 응답 DTO로 변환한다 (컨버터 이용)
        return ReviewConverter.toReviewPreviewListDTO(result);
    }

    //나의 리뷰 조회 기존에 비슷한 메소드 만들었는데 페이징이 아니라 그냥 새로 만듬.
    public ReviewResDTO.MyReviewListDTO findMyReviews(Long memberId,
                                                         Integer page){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        //- 가게에 맞는 리뷰를 가져온다 (Offset 페이징)
        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<Review> result = reviewRepository.findAllByMember(member, pageRequest);

        //- 결과를 응답 DTO로 변환한다 (컨버터 이용)
        return ReviewConverter.toMyReviewListDTO(result);
    }
}
