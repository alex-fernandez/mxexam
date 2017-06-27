package com.alexfrndz.orderexam.pojo.exception;

public class NotFoundException extends ClientException {

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public NotFoundException(String code, String msg) {
        super(code, msg);
        this.code = code;
    }
}
