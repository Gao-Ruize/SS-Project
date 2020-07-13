package com.ss.ssproj.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "read_jwc_msg")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class ReadJwcMsg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    int id;
    String studentid;
    String tutorid;
    int ifread;
    int ifstudent;
    int jwcmsgId;

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setTutorid(String tutorid) {
        this.tutorid = tutorid;
    }

    public String getTutorid() {
        return tutorid;
    }

    public void setIfread(int ifread) {
        this.ifread = ifread;
    }

    public int getIfread() {
        return ifread;
    }

    public void setIfstudent(int ifstudent) {
        this.ifstudent = ifstudent;
    }

    public int getIfstudent() {
        return ifstudent;
    }

    public void setJwcmsgId(int jwcmsgId) {
        this.jwcmsgId = jwcmsgId;
    }

    public int getJwcmsgId() {
        return jwcmsgId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
