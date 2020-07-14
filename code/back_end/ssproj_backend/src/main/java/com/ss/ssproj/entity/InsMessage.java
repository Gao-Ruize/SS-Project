package com.ss.ssproj.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "ins_message")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class InsMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    int id;
    String tutorid;
    String title;
    String content;
    String releasetime;
    int phase;

    public void setPhase(int phase) {
        this.phase = phase;
    }

    public int getPhase() {
        return phase;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setReleasetime(String releasetime) {
        this.releasetime = releasetime;
    }

    public String getReleasetime() {
        return releasetime;
    }


    public void setTutorid(String tutorid) {
        this.tutorid = tutorid;
    }

    public String getTutorid() {
        return tutorid;
    }

}


