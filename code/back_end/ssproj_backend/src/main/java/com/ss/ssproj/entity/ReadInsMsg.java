package com.ss.ssproj.entity;

import javax.persistence.*;

@Entity
@Table(name = "read_ins_msg")
public class ReadInsMsg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    int id;
    String studentid;
    int ifread;
    int msgid;

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

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setIfread(int ifread) {
        this.ifread = ifread;
    }

    public int getIfread() {
        return ifread;
    }
}
