package com.part2.comarket.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // 예외처리에 대해서 AOP를 적용하기 위해 사용
public class ApiExceptionHandler extends RuntimeException {

    // 커스텀 예외처리
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        ErrorCode code = e.getCode();
        return ErrorResponse.of(code);
    }

}
