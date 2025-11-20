package com.example.umc9th.domain.member.controller;

import com.example.umc9th.domain.member.dto.MemberMissionDTO;
import com.example.umc9th.domain.member.dto.MemberMissionResDTO;
import com.example.umc9th.domain.member.enums.Status;
import com.example.umc9th.domain.member.exception.code.MemberMissionSuccessCode;
import com.example.umc9th.domain.member.repository.MemberMissionRepository;
import com.example.umc9th.domain.member.service.MemberMissionService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberMissionController {

    private final MemberMissionRepository memberMissionRepository;
    private final MemberMissionService memberMissionService;

    //test 용임 controller에서 repo 바로 접근 비추
    @GetMapping("/member/{memberId}/missions")
    public List<MemberMissionDTO> getMissionsByStatus(
            @PathVariable Long memberId,
            @RequestParam Status status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return memberMissionRepository.findMissionsByStatus(
                memberId, status, pageable
        );
    }

    //특정 미션을 도전 중인 미션에 추가
    @PostMapping("/missions/{missionId}/participate")
    public ApiResponse<MemberMissionResDTO.participate> participate(
            @PathVariable Long missionId,
            Long memberId //TODO : 수정예정
    ) {

        MemberMissionResDTO.participate participate = memberMissionService.participateMission(missionId, memberId);

        return ApiResponse.onSuccess(MemberMissionSuccessCode.CREATED, participate);
    }

}
