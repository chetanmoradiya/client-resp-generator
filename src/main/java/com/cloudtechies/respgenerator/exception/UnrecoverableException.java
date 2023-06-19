package com.cloudtechies.respgenerator.exception;

import lombok.Getter;

public class UnrecoverableException extends RuntimeException {

    @Getter
    private String errorCode;

    public UnrecoverableException(String code, String desc) {
        super(desc);
        this.errorCode = code;
    }

    public UnrecoverableException(String message) {
        super(message);
    }
}
