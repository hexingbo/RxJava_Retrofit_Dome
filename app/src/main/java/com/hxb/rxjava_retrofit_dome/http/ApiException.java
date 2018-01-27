package com.hxb.rxjava_retrofit_dome.http;


public class ApiException extends IllegalArgumentException {

    private int code;

    public ApiException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
