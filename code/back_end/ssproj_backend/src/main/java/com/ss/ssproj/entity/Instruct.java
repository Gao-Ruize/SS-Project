package com.ss.ssproj.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

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

    public Instruct() {}

    public Instruct(int id, String studentid, String tutorid){
        this.id = id;
        this.studentid = studentid;
        this.tutorid = tutorid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false ;

        if (!(o instanceof Instruct)) return false;
        Instruct other = (Instruct) o;
        return this.id == other.id &&
                Objects.equals(this.studentid, other.studentid) &&
                Objects.equals(this.tutorid, other.tutorid);
    }
}
