package com.github.jianqibot.livecoursemall.exceptions.model;

public class HttpException extends RuntimeException {

    private final int httpStatus;
    private final String errorMessage;

    public HttpException(int httpStatus, String errorMessage) {
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
