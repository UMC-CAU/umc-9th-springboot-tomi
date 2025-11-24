package com.example.umc9th.domain.mission.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode{

    MISSION_RETRIEVED(HttpStatus.OK, "MISSION200", "미션 목록을 성공적으로 조회했습니다."),
    MISSION_CREATED(HttpStatus.CREATED, "MISSION201", "미션이 성공적으로 생성되었습니다.");


    private final HttpStatus status;
    private final String code;
    private final String message;
}
