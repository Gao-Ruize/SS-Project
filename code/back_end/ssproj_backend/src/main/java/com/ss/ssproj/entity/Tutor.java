package com.ss.ssproj.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

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

    public Tutor() {}

    public Tutor(int id, String tutorid, String uid, String tutorname){
        this.id = id;
        this.tutorid = tutorid;
        this.uid = uid;
        this.tutorname = tutorname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false ;

        if (!(o instanceof Tutor)) return false;
        Tutor other = (Tutor) o;
        return this.id == other.id &&
                Objects.equals(this.tutorid, other.tutorid) &&
                Objects.equals(this.uid, other.uid) &&
                Objects.equals(this.tutorname, other.tutorname);
    }
}
