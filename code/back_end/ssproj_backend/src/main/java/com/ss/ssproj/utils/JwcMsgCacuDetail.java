package com.ss.ssproj.utils;

import com.ss.ssproj.entity.Student;
import com.ss.ssproj.entity.Tutor;

import java.util.List;

public class JwcMsgCacuDetail {
    int msgid;
    String title;
    String content;
    String releasetime;
    List<Student> unreadStudents;
    List<Tutor> unreadTutors;

    public void setReleasetime(String releasetime) {
        this.releasetime = releasetime;
    }

    public String getReleasetime() {
        return this.releasetime;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setMsgid(int msgid) {
        this.msgid = msgid;
    }

    public int getMsgid() {
        return this.msgid;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    public void setUnreadStudents(List<Student> unreadStudents) {
        this.unreadStudents = unreadStudents;
    }

    public List<Student> getUnreadStudents() {
        return this.unreadStudents;
    }

    public void setUnreadTutors(List<Tutor> unreadTutors) {
        this.unreadTutors = unreadTutors;
    }

    public List<Tutor> getUnreadTutors() {
        return this.unreadTutors;
    }

    public JwcMsgCacuDetail () {}

    public JwcMsgCacuDetail(int msgid, String title, String content, String releasetime, List<Student> unreadStudents, List<Tutor> unreadTutors) {
        this.msgid = msgid;
        this.title = title;
        this.content = content;
        this.releasetime = releasetime;
        this.unreadStudents = unreadStudents;
        this.unreadTutors = unreadTutors;
    }
}
