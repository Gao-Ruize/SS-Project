package com.ss.ssproj.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "jwc_message")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class JwcMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    int id;
    String releasetime;
    String title;
    String content;
    int phase;

    public void setReleasetime(String releasetime) {
        this.releasetime = releasetime;
    }

    public String getReleasetime() {
        return releasetime;
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

    public void setPhase(int phase) {
        this.phase = phase;
    }

    public int getPhase() {
        return phase;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


    //在dao层将数据组装好
    //将jwc message 的信息与是否阅读组装
    @Transient
    int ifRead;

    public void setIfRead(int ifRead) {
        this.ifRead = ifRead;
    }

    public int getIfRead() {
        return ifRead;
    }
}
