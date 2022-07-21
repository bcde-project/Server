package com.bcdeproject.global.image.exception;

import com.bcdeproject.global.exception.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum ImageExceptionType implements BaseExceptionType {

    IMAGE_CAN_NOT_SAVE(10000, HttpStatus.BAD_REQUEST, "파일 저장에 실패했습니다."),
    IMAGE_CAN_NOT_DELETE(10001, HttpStatus.BAD_REQUEST, "파일 삭제에 실패했습니다.");


    private int errorCode;
    private HttpStatus httpStatus;
    private String errorMessage;

    ImageExceptionType(int errorCode, HttpStatus httpStatus, String errorMessage) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }

    @Override
    public int getErrorCode() {
        return this.errorCode;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    @Override
    public String getErrorMessage() {
        return this.errorMessage;
    }
}