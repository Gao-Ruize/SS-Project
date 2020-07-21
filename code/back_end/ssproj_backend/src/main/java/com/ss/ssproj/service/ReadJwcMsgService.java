package com.ss.ssproj.service;

import com.ss.ssproj.entity.JwcMessage;
import com.ss.ssproj.entity.ReadJwcMsg;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReadJwcMsgService {
    ReadJwcMsg findDistinctByStudentidAndMsgid(String studentid, int msgid);
    ReadJwcMsg findDistinctByTutoridAndMsgid(String tutorid, int msgid);
    ReadJwcMsg saveOrUpdate(ReadJwcMsg readJwcMsg);
    List<ReadJwcMsg> findAllByStudentidAndIfread(String studentid, int ifread);
    List<ReadJwcMsg> findAllByTutoridAndIfread(String tutorid, int ifread);
    List<ReadJwcMsg> findAllByIfstudentAndIfreadAndMsgid(int ifstudent, int ifread, int msgid);
}
