package com.example.umc9th.domain.mission.dto;

import com.example.umc9th.domain.review.dto.ReviewResDTO;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class MissionResDTO {

    @Builder
    public record StoreMissionListDTO(
            List<MissionResDTO.StoreMissionDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    @Builder
    public record StoreMissionDTO(
            Long missionId,
            String missionName,
            String storeName,
            Float score,
            String body,
            LocalDate deadline
    ){}


    @Builder
    public record MyMissionListDTO(
            List<MissionResDTO.MyMissionDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    @Builder
    public record MyMissionDTO(
            Long missionId,
            String missionName,
            String storeName,
            Float score,
            String body,
            LocalDate createdAt
    ){}
}
