package com.ss.ssproj.utils;

import java.util.Objects;

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

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null) return false;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StuInfo stuInfo = (StuInfo) o;
        return id == stuInfo.id &&
                Objects.equals(studentid, stuInfo.studentid) &&
                Objects.equals(uid, stuInfo.uid) &&
                Objects.equals(studentname, stuInfo.studentname) &&
                Objects.equals(tutorid, stuInfo.tutorid) &&
                Objects.equals(tutorname, stuInfo.tutorname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, studentid, uid, studentname, tutorid, tutorname);
    }
//
//        if (!(o instanceof StuInfo)) return false;
//        StuInfo other = (StuInfo) o;
//        return this.id == other.id &&
//                Objects.equals(this.studentid, other.studentid) &&
//                Objects.equals(this.uid, other.uid) &&
//                Objects.equals(this.studentname, other.studentname) &&
//                Objects.equals(this.tutorid, other.tutorid) &&
//                Objects.equals(this.tutorname, other.tutorname);
//    }
}
