package com.example.umc9th.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class MissionRegionDto {

    private Long regionId;
    private String regionName;
    private Long missionId;
    private String content;
    private LocalDateTime deadline;
    private int score;
}
