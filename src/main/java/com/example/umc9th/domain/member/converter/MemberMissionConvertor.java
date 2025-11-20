package com.example.umc9th.domain.member.converter;

import com.example.umc9th.domain.member.dto.MemberMissionResDTO;
import com.example.umc9th.domain.member.entity.MemberMission;

public class MemberMissionConvertor {
    public static MemberMissionResDTO.participate toMemberMissionDTO(MemberMission entity) {
        return new MemberMissionResDTO.participate(
                entity.getId(),
                entity.getMission().getId(),
                entity.getMember().getId(),
                entity.getStatus().name()
        );
    }
}
