package com.ss.ssproj.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "jwc_message")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class JwcMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jwc_msg_id")
    int jwc_msg_id;
    int release_time;
    String title;
    String content;

    public int getJwc_msg_id() {
        return jwc_msg_id;
    }

    public void setJwc_msg_id(int jwc_msg_id) {
        this.jwc_msg_id = jwc_msg_id;
    }

    public int getRelease_time() {
        return release_time;
    }

    public void setRelease_time(int release_time) {
        this.release_time = release_time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
