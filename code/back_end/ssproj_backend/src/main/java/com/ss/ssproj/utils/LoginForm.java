package com.ss.ssproj.utils;

public class LoginForm {
    private
    int u_id;
    int real_id;
    public LoginForm(int U_id, int Real_id) {
        this.u_id = U_id;
        this.real_id = Real_id;
    }

    public int getReal_id() {
        return real_id;
    }

    public void setReal_id(int real_id) {
        this.real_id = real_id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }
}
