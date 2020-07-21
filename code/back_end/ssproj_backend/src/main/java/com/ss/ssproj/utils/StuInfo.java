package com.ss.ssproj.utils;

public class StuInfo {
    int id;
    String studentid;
    String uid;
    String studentname;
    String tutorid;
    String tutorname;

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getStudentid() {
        return this.studentid;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setTutorname(String tutorname) {
        this.tutorname = tutorname;
    }

    public String getTutorname() {
        return this.tutorname;
    }

    public void setTutorid(String tutorid) {
        this.tutorid = tutorid;
    }

    public String getTutorid() {
        return this.tutorid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUid() {
        return this.uid;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getStudentname() {
        return this.studentname;
    }

    public StuInfo () {}

    public StuInfo (int id, String studentid, String uid, String studentname, String tutorid, String tutorname) {
        this.id = id;
        this.studentid = studentid;
        this.uid = uid;
        this.studentname = studentname;
        this.tutorid = tutorid;
        this.tutorname = tutorname;
    }
}
