package com.bacujlb.webfluxdemo.dto;

public class InputFailedValidationResponse {
    private int errorCode;
    private int input;
    private String message;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getInput() {
        return input;
    }

    public void setInput(int input) {
        this.input = input;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "InputFailedValidationResponse{" +
                "errorCode=" + errorCode +
                ", input=" + input +
                ", message='" + message + '\'' +
                '}';
    }
}
