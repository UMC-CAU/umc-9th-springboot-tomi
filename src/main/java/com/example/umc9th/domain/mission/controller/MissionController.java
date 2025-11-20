package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.MissionRegionDto;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MissionController {

    private final MissionRepository missionRepository;

    //test 용 추후 수정
    @GetMapping("/region/{regionId}/available")
    public List<MissionRegionDto> getAvailableMissions(
            @PathVariable Long regionId,
            @RequestParam Long memberId,
            @RequestParam(required = false) LocalDateTime cursorCreatedAt,
            @RequestParam(required = false) Long cursorMissionId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return missionRepository.findAvailableMissionsByRegion(
                memberId, regionId, cursorCreatedAt, cursorMissionId, pageable
        );
    }


}
