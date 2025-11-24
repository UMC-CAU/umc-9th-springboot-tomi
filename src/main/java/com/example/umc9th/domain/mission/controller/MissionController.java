package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.MissionRegionDto;
import com.example.umc9th.domain.mission.dto.MissionResDTO;
import com.example.umc9th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.domain.mission.service.MissionService;
import com.example.umc9th.global.annotation.ValidPage;
import com.example.umc9th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    //private final MissionRepository missionRepository;
    private final MissionService missionService;


    //ch 9
    @Operation(
            summary = "특정 가게의 미션 목록 조회 API",
            description = "특정 가게의 미션을 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @GetMapping("/stores/{storeId}/missions")
    public ApiResponse<MissionResDTO.StoreMissionListDTO> getStoreMissions(
            @PathVariable Long storeId,
            @ValidPage @RequestParam(defaultValue = "1") Integer page
    ){
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_RETRIEVED, missionService.findStoreMissions(storeId, page - 1));
    }

    //ch 9
    @Operation(
            summary = "내가 진행중인 미션 목록 조회 API",
            description = "내가 진행중인 미션을 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @GetMapping("/missions/my/challenge")
    public ApiResponse<MissionResDTO.MyMissionListDTO> getSwtoreMissions(
            @RequestParam Long memberId,  //TODO 우선 임시로
            @ValidPage @RequestParam(defaultValue = "1") Integer page
    ){
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_RETRIEVED, missionService.findMyMissions(memberId,page - 1));
    }
}
