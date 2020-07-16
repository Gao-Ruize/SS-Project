package com.ss.ssproj.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

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

    public JwcMessage() {}

    public JwcMessage(int id, String releasetime, String title, String content, int phase){
        this.id = id;
        this.releasetime = releasetime;
        this.title = title;
        this.content = content;
        this.phase = phase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false ;

        if (!(o instanceof JwcMessage)) return false;
        JwcMessage other = (JwcMessage) o;
        return this.id == other.id &&
                Objects.equals(this.releasetime, other.releasetime) &&
                Objects.equals(this.title, other.title) &&
                Objects.equals(this.content, other.content) &&
                this.phase == other.phase;
    }
}
