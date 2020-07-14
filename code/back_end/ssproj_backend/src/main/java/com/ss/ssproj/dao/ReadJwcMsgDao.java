package com.ss.ssproj.dao;

import com.ss.ssproj.entity.ReadJwcMsg;

public interface ReadJwcMsgDao {
    ReadJwcMsg findDistinctByStudentidAndMsgid(String studentid, int msgid);
    ReadJwcMsg findDistinctByTutoridAndMsgid(String tutorid, int msgid);
    ReadJwcMsg save(ReadJwcMsg readJwcMsg);
}
