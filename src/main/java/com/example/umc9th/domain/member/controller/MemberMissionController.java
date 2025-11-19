package com.example.umc9th.domain.member.controller;

import com.example.umc9th.domain.member.dto.MemberMissionDto;
import com.example.umc9th.domain.member.dto.MemberMissionResDTO;
import com.example.umc9th.domain.member.entity.MemberMission;
import com.example.umc9th.domain.member.enums.Status;
import com.example.umc9th.domain.member.repository.MemberMissionRepository;
import com.example.umc9th.domain.member.service.MemberMissionService;
import com.example.umc9th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc9th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberMissionController {

    private final MemberMissionRepository memberMissionRepository;
    private final MemberMissionService memberMissionService;

    //test 용임 controller에서 repo 바로 접근 비추
    @GetMapping("/member/{memberId}/missions")
    public List<MemberMissionDto> getMissionsByStatus(
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

    @PostMapping("/missions/{missionId}/participate")
    public ApiResponse<MemberMissionResDTO.participate> participate(
            @PathVariable Long missionId,
            Long memberId //TODO : 수정예정
    ) {

        MemberMissionResDTO.participate c = memberMissionService.participateMission(missionId, memberId);

        return ApiResponse.onSuccess(ReviewSuccessCode.CREATED, c);
    }

}
