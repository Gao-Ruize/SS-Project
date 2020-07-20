package com.ss.ssproj.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

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

    public InsMessage() {}

    public InsMessage(int id, String tutorid, String title, String content, String releasetime){
        this.id = id;
        this.tutorid = tutorid;
        this.title = title;
        this.content = content;
        this.releasetime = releasetime;
    }

    @Transient
    int ifRead;

    public void setIfRead(int ifRead) {
        this.ifRead = ifRead;
    }

    public int getIfRead() {
        return this.ifRead;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false ;

        if (!(o instanceof InsMessage)) return false;
        InsMessage other = (InsMessage) o;
        return this.id == other.id &&
                Objects.equals(this.tutorid, other.tutorid) &&
                Objects.equals(this.title, other.title) &&
                Objects.equals(this.content, other.content) &&
                Objects.equals(this.releasetime, other.releasetime);
    }
}


