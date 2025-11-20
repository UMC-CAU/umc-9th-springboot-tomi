package com.example.umc9th.domain.member.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberMissionSuccessCode implements BaseSuccessCode {

    CREATED(HttpStatus.CREATED, "MISSION201_1", "미션 참여가 정상적으로 등록되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
