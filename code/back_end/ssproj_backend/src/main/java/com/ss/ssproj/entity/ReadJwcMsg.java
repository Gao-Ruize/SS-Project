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
    int rjm_id;
    String student_id;
    String tutor_id;
    int if_read;
    int if_student;

    public int getIf_read() {
        return if_read;
    }

    public void setIf_read(int if_read) {
        this.if_read = if_read;
    }

    public String getTutor_id() {
        return tutor_id;
    }

    public void setTutor_id(String tutor_id) {
        this.tutor_id = tutor_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public int getIf_student() {
        return if_student;
    }

    public void setIf_student(int if_student) {
        this.if_student = if_student;
    }

    public int getRjm_id() {
        return rjm_id;
    }

    public void setRjm_id(int rjm_id) {
        this.rjm_id = rjm_id;
    }
}
