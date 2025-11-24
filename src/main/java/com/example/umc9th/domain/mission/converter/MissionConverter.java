package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.member.entity.MemberMission;
import com.example.umc9th.domain.mission.dto.MissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    // Page<Mission> -> StoreMissionListDTO 변환
    public static MissionResDTO.StoreMissionListDTO toStoreMissionListDTO(Page<Mission> result) {
        return MissionResDTO.StoreMissionListDTO.builder()
                .missionList(result.getContent().stream()
                        .map(MissionConverter::toStoreMissionDTO)
                        .collect(Collectors.toList())
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    // Mission -> StoreMissionDTO 변환
    public static MissionResDTO.StoreMissionDTO toStoreMissionDTO(Mission mission) {
        return MissionResDTO.StoreMissionDTO.builder()
                .missionId(mission.getId())
                .missionName(mission.getName())
                .storeName(mission.getStore().getName())
                .score(mission.getScore())
                .body(mission.getContent())
                .deadline(mission.getDeadline())
                .build();
    }

    public static MissionResDTO.MyMissionDTO toMyMissionDTO(Mission mission) {
        return MissionResDTO.MyMissionDTO.builder()
                .missionId(mission.getId())
                .missionName(mission.getName())
                .storeName(mission.getStore().getName())
                .score(mission.getScore())
                .body(mission.getContent())
                .createdAt(mission.getCreatedAt().toLocalDate())  // LocalDate 변환
                .build();
    }

    public static MissionResDTO.MyMissionListDTO toMyMissionListDTO(Page<Mission> missionPage) {

        List<MissionResDTO.MyMissionDTO> missionList = missionPage.getContent().stream()
                .map(MissionConverter::toMyMissionDTO)
                .toList();

        return MissionResDTO.MyMissionListDTO.builder()
                .missionList(missionList)
                .listSize(missionList.size())
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .isFirst(missionPage.isFirst())
                .isLast(missionPage.isLast())
                .build();
    }
}