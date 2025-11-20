package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.dto.ReviewSearchDTO;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.entity.QRegion;
import com.example.umc9th.domain.store.entity.QStore;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Review> searchMyReview(ReviewSearchDTO reviewSearchDTO) {

        QReview review = QReview.review;
        BooleanBuilder builder = new BooleanBuilder();

        if(reviewSearchDTO.getMemberId() != null) {
            builder.and(review.member.id.eq(reviewSearchDTO.getMemberId()));
        }
        if(reviewSearchDTO.getStoreId() != null) {
            builder.and(review.store.id.eq(reviewSearchDTO.getStoreId()));
        }
        if(reviewSearchDTO.getScore() != null){
            float min = (float) reviewSearchDTO.getScore();
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
