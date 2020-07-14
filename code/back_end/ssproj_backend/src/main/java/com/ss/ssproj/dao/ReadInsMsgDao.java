package com.ss.ssproj.dao;

import com.ss.ssproj.entity.ReadInsMsg;

import java.util.List;

public interface ReadInsMsgDao {
    ReadInsMsg findDistinctByStudentidAndMsgid(String studentid, int msgid);
    ReadInsMsg save(ReadInsMsg readInsMsg);
    List<ReadInsMsg> findAllByStudentid(String studentid);
    List<ReadInsMsg> findAllByMsgid(int msgid);
}
