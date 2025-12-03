package com.example.umc9th.domain.member.dto;

import com.example.umc9th.domain.member.entity.Member;
import lombok.Builder;

import java.time.LocalDateTime;

public class MemberResDTO {
    @Builder
    public record JoinDTO(
            Long memberId,
            LocalDateTime createAt
    ){}

    // 로그인
    @Builder
    public record LoginDTO(
            Member member,
            String accessToken
    ){}
}
