package com.ss.ssproj.utils;

public class LoginForm {
    private
    String u_id;
    String real_id;
    String type;

    public LoginForm(String U_id, String Real_id, String t) {
        this.u_id = U_id;
        this.real_id = Real_id;
        this.type = t;
    }

    public String getReal_id() {
        return real_id;
    }

    public void setReal_id(String real_id) {
        this.real_id = real_id;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
