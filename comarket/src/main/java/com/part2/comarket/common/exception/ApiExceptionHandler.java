package com.part2.comarket.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // 예외처리에 대해서 AOP를 적용하기 위해 사용
public class ApiExceptionHandler extends RuntimeException {


    // 유효성 검사 예외처리
    @ExceptionHandler(MethodArgumentNotValidException .class) //예외가 발생한 요청을 처리하기 위해
    public ResponseEntity<ErrorResponse> handleMethodNotValidException(MethodArgumentNotValidException e){
        final String[] message = {""};

        e.getBindingResult().getAllErrors()
                .forEach(c -> message[0] = c.getDefaultMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .msg(message[0])
                        .errorCode("400")
                        .httpStatus(HttpStatus.BAD_REQUEST)
                        .build()
                );
    }

    // 잘못된 요청 예외처리
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .msg(ex.getMessage())
                        .errorCode("400")
                        .httpStatus(HttpStatus.BAD_REQUEST)
                        .build());
    }

    // 커스텀 예외처리
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        ErrorCode code = e.getCode();
        return ErrorResponse.of(code);
    }

}
