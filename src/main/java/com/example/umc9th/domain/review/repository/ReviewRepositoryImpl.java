package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Review> searchMyReview(Long memberId, Long storeId, Integer score) {

        QReview review = QReview.review;
        BooleanBuilder builder = new BooleanBuilder();

        if(memberId != null) {
            builder.and(review.member.id.eq(memberId));
        }
        if(storeId != null) {
            builder.and(review.store.id.eq(storeId));
        }
        if(score != null){
            float min = (float) score;
            float max = min + 1.0f;
            builder.and(review.score.goe(min).and(review.score.lt(max)));
        }

        return queryFactory
                .selectFrom(review)
                .where(builder)
                .orderBy(review.createdAt.desc())
                .fetch();
    }
}
