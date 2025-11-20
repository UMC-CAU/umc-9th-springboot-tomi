package com.example.umc9th.domain.member.service;

import com.example.umc9th.domain.member.converter.MemberMissionConvertor;
import com.example.umc9th.domain.member.dto.MemberMissionDto;
import com.example.umc9th.domain.member.dto.MemberMissionResDTO;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.entity.MemberMission;
import com.example.umc9th.domain.member.enums.Status;
import com.example.umc9th.domain.member.exception.MemberException;
import com.example.umc9th.domain.member.exception.code.MemberErrorCode;
import com.example.umc9th.domain.member.repository.MemberMissionRepository;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.exception.MissionException;
import com.example.umc9th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberMissionService {
    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;

    public MemberMissionResDTO.participate participateMission(Long missionId, Long memberId) {


        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        MemberMission challenge = MemberMission.builder()
                .mission(mission)
                .member(member)
                .status(Status.CHALLENGE)
                .build();

        MemberMission save = memberMissionRepository.save(challenge);

        return MemberMissionConvertor.toMemberMissionDTO(save);
    }
}
