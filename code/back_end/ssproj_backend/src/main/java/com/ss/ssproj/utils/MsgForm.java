package com.ss.ssproj.utils;

import com.ss.ssproj.entity.JwcMessage;
import com.ss.ssproj.entity.Tutor;

import java.util.List;
import java.util.Objects;

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

    //新加入了phase
    //在导师发送信息的时候需要选择phase
    int phase;

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

    public void setPhase(int phase) {
        this.phase = phase;
    }

    public int getPhase() {
        return this.phase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false ;

        if (!(o instanceof MsgForm)) return false;
        MsgForm other = (MsgForm) o;
        return  Objects.equals(this.content,other.content) &&
                Objects.equals(this.title, other.title) &&
                Objects.equals(this.time, other.time) &&
                Objects.equals(this.senderName, other.senderName) &&
                Objects.equals(this.msgType,other.msgType) &&
                Objects.equals(this.tutorId,other.tutorId) &&
                Objects.equals(this.toIds,other.toIds) &&
                this.msgId == other.msgId;
    }

    public MsgForm() {}

    public MsgForm(String title, String content, String time,
                      String senderName, String msgType, String tutorId,
                      int msgId, List<String> toIds) {
        this.title = title;
        this.time = time;
        this.content = content;
        this.senderName = senderName;
        this.msgType = msgType;
        this.tutorId = tutorId;
        this.msgId = msgId;
        this.toIds = toIds;
    }
}
