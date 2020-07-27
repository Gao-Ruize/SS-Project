package com.ss.ssproj.utils;

public class LoginMsg {
    String realId;
    String type;
    String openId;
    String token;

    public void setRealId(String realId) {
        this.realId = realId;
    }

    public String getRealId() {
        return realId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getOpenId() {
        return openId;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
