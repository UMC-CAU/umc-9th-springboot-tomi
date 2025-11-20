package com.example.umc9th.domain.member.repository;

import com.example.umc9th.domain.member.dto.MemberMissionDTO;
import com.example.umc9th.domain.member.entity.MemberMission;
import com.example.umc9th.domain.member.enums.Status;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    @Query("""
    SELECT new com.example.umc9th.domain.member.dto.MemberMissionDTO(m.id, mis.id,mis.content,mis.score,mm.status,st.name)
    FROM MemberMission mm
    JOIN mm.member m
    JOIN mm.mission mis
    JOIN mis.store st
    WHERE m.id = :memberId AND mm.status = :status 
    ORDER BY mis.id DESC
    """)
    public List<MemberMissionDTO> findMissionsByStatus(@Param("memberId") Long memberId,
                                                       @Param("status") Status status, Pageable pageable);
    //memberId는 나 즉 Member의 id 값으로 조회하기 위해 필요, status는 진행중, 완료 두 개의 메소드로 분리하는 것보다 이렇게 매개변수로 받는 것이 더 좋다고 생각함.
    //pageable은 페이징 구현하기 위해 넣음.
}
