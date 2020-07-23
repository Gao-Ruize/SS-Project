package com.ss.ssproj.utils;

public class Result {
    private int code;
    private String token;

    public Result(int Code) {
        this.code = Code;
        this.token = "";
    }

    public Result(int code, String token) {
        this.code = code;
        this.token = token;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
