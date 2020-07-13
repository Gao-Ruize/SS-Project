package com.ss.ssproj.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "instruct")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class Instruct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    int id;
    String studentid;
    String tutorid;

    public void setTutorid(String tutorid) {
        this.tutorid = tutorid;
    }

    public String getTutorid() {
        return tutorid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
