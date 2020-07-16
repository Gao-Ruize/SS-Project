package com.ss.ssproj.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

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
    int msgid;

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

    public void setMsgid(int msgid) {
        this.msgid = msgid;
    }

    public int getMsgid() {
        return msgid;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public ReadJwcMsg() {}

    public ReadJwcMsg(int id, String studentid, String tutorid, int ifread, int ifstudent, int msgid){
        this.id = id;
        this.studentid = studentid;
        this.tutorid = tutorid;
        this.ifread = ifread;
        this.ifstudent = ifstudent;
        this.msgid = msgid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false ;

        if (!(o instanceof ReadJwcMsg)) return false;
        ReadJwcMsg other = (ReadJwcMsg) o;
        return this.id == other.id &&
                Objects.equals(this.studentid, other.studentid) &&
                Objects.equals(this.tutorid, other.tutorid) &&
                this.ifread == other.ifread &&
                this.ifstudent == other.ifstudent &&
                this.msgid == other.msgid;
    }
}
