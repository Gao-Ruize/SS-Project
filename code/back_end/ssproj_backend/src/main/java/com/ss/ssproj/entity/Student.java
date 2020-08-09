package com.ss.ssproj.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "student")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    int id;
    String studentid;
    String uid;
    String studentname;


    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Transient
    int ifRead;

    public void setIfRead(int ifRead) {
        this.ifRead = ifRead;
    }

    public int getIfRead() {
        return ifRead;
    }

    public Student() {}

    public Student(int id, String studentid, String uid, String studentname){
        this.id = id;
        this.studentid = studentid;
        this.uid = uid;
        this.studentname = studentname;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, studentid, uid, studentname, ifRead);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false ;

        if (!(o instanceof Student)) return false;
        Student other = (Student) o;
        return this.id == other.id &&
                Objects.equals(this.studentid, other.studentid) &&
                Objects.equals(this.uid, other.uid) &&
                Objects.equals(this.studentname, other.studentname);
    }
}
