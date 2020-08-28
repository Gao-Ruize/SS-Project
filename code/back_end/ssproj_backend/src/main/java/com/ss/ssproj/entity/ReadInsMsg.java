package com.ss.ssproj.entity;

import javax.persistence.*;
import java.util.Objects;

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
    String reply;

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

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getReply() {
        return this.reply;
    }

    public ReadInsMsg() {}

    public ReadInsMsg(int id, String studentid, int ifread, int msgid){
        this.id = id;
        this.studentid = studentid;
        this.ifread = ifread;
        this.msgid = msgid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false ;

        if (!(o instanceof ReadInsMsg)) return false;
        ReadInsMsg other = (ReadInsMsg) o;
        return this.id == other.id &&
                Objects.equals(this.studentid, other.studentid) &&
                this.ifread == other.ifread &&
                this.msgid == other.msgid;
    }
}
