package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.dto.MissionRegionDto;
import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {
    @Query("""
        SELECT new com.example.umc9th.domain.mission.dto.MissionRegionDto(
            r.id,r.name,m.id,m.content,m.deadline,m.score)
        FROM Region r
        JOIN r.stores s
        JOIN s.missions m
        LEFT OUTER JOIN MemberMission mm ON mm.mission = m AND mm.member.id = :memberId
        WHERE r.id = :regionId
          AND mm.member IS NULL
          AND m.deadline > CURRENT_TIMESTAMP
          AND (
                (:cursorCreatedAt IS NULL AND :cursorMissionId IS NULL)
                OR (m.createdAt < :cursorCreatedAt
                    OR (m.createdAt = :cursorCreatedAt AND m.id < :cursorMissionId))
              )
        ORDER BY m.createdAt DESC, m.id DESC
        """)
    List<MissionRegionDto> findAvailableMissionsByRegion(
            @Param("memberId") Long memberId, @Param("regionId") Long regionId,
            @Param("cursorCreatedAt") LocalDateTime cursorCreatedAt, @Param("cursorMissionId") Long cursorMissionId,
            Pageable pageable
    );
}
