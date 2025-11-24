package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {

    //쿼리 메소드 Review 조회 by member
    public List<Review> findByMemberId(Long memberId);

    Page<Review> findAllByStore(Store store, Pageable pageable);
    Page<Review> findAllByMember(Member member, Pageable pageable);

}
