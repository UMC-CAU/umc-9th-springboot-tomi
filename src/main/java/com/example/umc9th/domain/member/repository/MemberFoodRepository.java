package com.example.umc9th.domain.member.repository;

import com.example.umc9th.domain.member.entity.MemberFood;
import com.example.umc9th.domain.member.entity.MemberMission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberFoodRepository extends JpaRepository<MemberFood, Long> {
}
