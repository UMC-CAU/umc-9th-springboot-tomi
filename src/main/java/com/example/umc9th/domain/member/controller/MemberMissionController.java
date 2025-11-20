package com.example.umc9th.domain.member.controller;

import com.example.umc9th.domain.member.dto.MemberMissionDto;
import com.example.umc9th.domain.member.enums.Status;
import com.example.umc9th.domain.member.repository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberMissionController {

    private final MemberMissionRepository memberMissionRepository;

    //test 용임 controller에서 repo 바로 접근 비추
    @GetMapping("member/{memberId}/missions")
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

}
