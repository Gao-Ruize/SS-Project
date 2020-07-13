package com.ss.ssproj.utils;

public class RegisterForm {
    private
    String uId;
    String realId;
    String type;

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getuId() {
        return uId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRealId() {
        return realId;
    }

    public void setRealId(String realId) {
        this.realId = realId;
    }

    public String getType() {
        return type;
    }
}
