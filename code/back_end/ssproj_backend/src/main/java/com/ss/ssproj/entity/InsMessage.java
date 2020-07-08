package com.ss.ssproj.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "ins_message")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class InsMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ins_msg_id")
    int ins_msg_id;
    String tutor_id;
    String student_id;
    String title;
    String content;
    int release_time;
    int if_read;

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setTutor_id(String tutor_id) {
        this.tutor_id = tutor_id;
    }

    public String getTutor_id() {
        return tutor_id;
    }

    public void setIf_read(int if_read) {
        this.if_read = if_read;
    }

    public int getIf_read() {
        return if_read;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setIns_msg_id(int ins_msg_id) {
        this.ins_msg_id = ins_msg_id;
    }

    public int getIns_msg_id() {
        return ins_msg_id;
    }

    public void setRelease_time(int release_time) {
        this.release_time = release_time;
    }

    public int getRelease_time() {
        return release_time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}


