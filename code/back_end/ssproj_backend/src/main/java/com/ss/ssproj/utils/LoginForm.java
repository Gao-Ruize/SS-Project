package com.ss.ssproj.utils;

public class LoginForm {
    private
    String uId;
    String realId;
    String type;

    public LoginForm(String U_id, String Real_id, String t) {
        this.uId = U_id;
        this.realId = Real_id;
        this.type = t;
    }

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
