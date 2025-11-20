package com.example.umc9th.domain.member.dto;

import java.time.LocalDateTime;

public class MemberMissionResDTO {

    public record participate(
            Long challengeId,
            Long missionId,
            Long memberId,
            String status
    ) {}
}
