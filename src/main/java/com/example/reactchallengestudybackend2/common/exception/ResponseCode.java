package com.example.reactchallengestudybackend2.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseCode {

    SUCCESS(200, "성공"),

    INVALID_REQUEST(400, "잘못된 요청입니다."),
    NO_TARGET_BOARD(404, "해당하는 게시판이 없습니다."),
    NO_TARGET_COMMENT(404, "해당하는 댓글이 없습니다."),

    USER_NOT_FOUND(401, "해당되는 유저를 찾을 수 없습니다."),
    USERNAME_ALREADY_EXISTS(422, "이미 존재하는 회원입니다."),

    INTERNAL_SERVER_ERROR(500, "서버에 오류가 발생했습니다."),
    ;

    private final Integer code;
    private final String message;
}
