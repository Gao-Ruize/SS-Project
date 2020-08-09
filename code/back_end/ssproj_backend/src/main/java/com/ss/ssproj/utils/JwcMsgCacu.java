package com.ss.ssproj.utils;

import java.util.Objects;

public class JwcMsgCacu {
    int id;
    String title;
    String releasetime;
    int phase;
    int treadnum;
    int ttotnum;
    int sreadnum;
    int stotnum;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

    public int getPhase() {
        return this.phase;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setReleasetime(String releasetime) {
        this.releasetime = releasetime;
    }

    public String getReleasetime() {
        return this.releasetime;
    }

    public void setSreadnum(int sreadnum) {
        this.sreadnum = sreadnum;
    }

    public int getSreadnum() {
        return this.sreadnum;
    }

    public void setStotnum(int stotnum) {
        this.stotnum = stotnum;
    }

    public int getStotnum() {
        return this.stotnum;
    }

    public void setTreadnum(int treadnum) {
        this.treadnum = treadnum;
    }

    public int getTreadnum() {
        return this.treadnum;
    }

    public void setTtotnum(int ttotnum) {
        this.ttotnum = ttotnum;
    }

    public int getTtotnum() {
        return this.ttotnum;
    }

    public JwcMsgCacu () {}

    public JwcMsgCacu (int id, String title, String releasetime, int phase, int treadnum, int ttotnum, int sreadnum, int stotnum) {
        this.id = id;
        this.title = title;
        this.releasetime = releasetime;
        this.phase = phase;
        this.treadnum = treadnum;
        this.ttotnum = ttotnum;
        this.sreadnum = sreadnum;
        this.stotnum = stotnum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JwcMsgCacu that = (JwcMsgCacu) o;
        return id == that.id &&
                phase == that.phase &&
                treadnum == that.treadnum &&
                ttotnum == that.ttotnum &&
                sreadnum == that.sreadnum &&
                stotnum == that.stotnum &&
                Objects.equals(title, that.title) &&
                Objects.equals(releasetime, that.releasetime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, releasetime, phase, treadnum, ttotnum, sreadnum, stotnum);
    }
}
