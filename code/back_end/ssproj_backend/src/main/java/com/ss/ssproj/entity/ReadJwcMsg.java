package com.ss.ssproj.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "read_jwc_msg")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class ReadJwcMsg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rjm_id")
    int rjmId;
    String studentId;
    String tutorId;
    int ifRead;
    int ifStudent;

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setTutorId(String tutorId) {
        this.tutorId = tutorId;
    }

    public String getTutorId() {
        return tutorId;
    }

    public void setIfRead(int ifRead) {
        this.ifRead = ifRead;
    }

    public int getIfRead() {
        return ifRead;
    }

    public void setIfStudent(int ifStudent) {
        this.ifStudent = ifStudent;
    }

    public int getIfStudent() {
        return ifStudent;
    }

    public void setRjmId(int rjmId) {
        this.rjmId = rjmId;
    }

    public int getRjmId() {
        return rjmId;
    }
}
