package com.ss.ssproj.dao;

import com.ss.ssproj.entity.ReadJwcMsg;

import java.util.List;

public interface ReadJwcMsgDao {
    ReadJwcMsg findDistinctByStudentidAndMsgid(String studentid, int msgid);
    ReadJwcMsg findDistinctByTutoridAndMsgid(String tutorid, int msgid);
    ReadJwcMsg save(ReadJwcMsg readJwcMsg);
    List<ReadJwcMsg> findAllByStudentidAndIfread(String studentid, int ifread);
}
