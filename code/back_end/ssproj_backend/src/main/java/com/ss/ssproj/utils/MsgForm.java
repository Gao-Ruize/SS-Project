package com.ss.ssproj.utils;

public class MsgForm {
    private
    String title;
    String content;
    String time;

    public MsgForm(String t, String c, String ti) {
        this.title = t;
        this.content = t;
        this.time = ti;
    }

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
}
