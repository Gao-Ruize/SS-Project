package com.ss.ssproj.utils;

public class ReadMsgForm {
    String userId;
    int msgId;
    String type;
    String userType;
    String reply;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserType() {
        return userType;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getReply() {
        return this.reply;
    }
}
