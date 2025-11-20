package com.example.umc9th.domain.member.dto;

import com.example.umc9th.domain.member.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberMissionDTO {
    private Long member_id;
    private Long mission_id;
    private String content;
    private int score;
    private Status status;
    private String storeName;
}
