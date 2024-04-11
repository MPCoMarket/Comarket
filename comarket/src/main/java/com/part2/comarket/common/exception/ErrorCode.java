package com.part2.comarket.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    
    COMPANY_NOT_FOUND(HttpStatus.NOT_FOUND, "404", "Company not found."),
    COMPANY_CRAWLING_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "500", "SSL Handshake Error"),
    ;

    private HttpStatus httpStatus;
    private String errorCode;
    private String msg;


    ErrorCode(HttpStatus httpStatus, String errorCode, String msg) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.msg = msg;
    }
}
