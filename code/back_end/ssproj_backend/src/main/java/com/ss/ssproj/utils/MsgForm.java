package com.ss.ssproj.utils;

import java.util.List;

public class MsgForm {
    private
    String title;
    String content;
    String time;
    String senderName;
    String msgType;
    String tutorId;
    int msgId;
    List<String> toIds;

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public int getMsgId() {
        return msgId;
    }

    public void setTutorId(String tutorId) {
        this.tutorId = tutorId;
    }

    public String getTutorId() {
        return tutorId;
    }

    public void setToIds(List<String> toIds) {
        this.toIds = toIds;
    }

    public List<String> getToIds() {
        return toIds;
    }
}
