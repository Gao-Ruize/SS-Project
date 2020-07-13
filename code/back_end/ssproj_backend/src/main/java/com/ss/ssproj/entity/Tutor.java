package com.ss.ssproj.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "tutor")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    int id;
    String tutorid;
    String uid;
    String tutorname;


    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTutorid(String tutorid) {
        this.tutorid = tutorid;
    }

    public String getTutorid() {
        return tutorid;
    }

    public void setTutorname(String tutorname) {
        this.tutorname = tutorname;
    }

    public String getTutorname() {
        return tutorname;
    }
}
