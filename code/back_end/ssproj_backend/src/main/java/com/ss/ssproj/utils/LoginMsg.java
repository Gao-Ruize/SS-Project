package com.ss.ssproj.utils;

public class LoginMsg {
    String codeId;
    String msg;

    public LoginMsg(String c, String m) {
        this.codeId = c;
        this.msg = m;
    }

    public String getCodeId() {
        return codeId;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
