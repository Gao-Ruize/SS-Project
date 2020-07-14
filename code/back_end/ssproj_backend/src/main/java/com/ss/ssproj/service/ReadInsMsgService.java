package com.ss.ssproj.service;

import com.ss.ssproj.entity.ReadInsMsg;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReadInsMsgService {
    ReadInsMsg findDistinctByStudentidAndMsgid(String studentid, int msgid);
    ReadInsMsg save(ReadInsMsg readInsMsg);
    List<ReadInsMsg> findAllByStudentid(String studentid);
    List<ReadInsMsg> findAllByMsgid(int msgid);
}
