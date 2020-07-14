package com.ss.ssproj.service;

import com.ss.ssproj.entity.JwcMessage;
import com.ss.ssproj.entity.ReadJwcMsg;
import org.springframework.stereotype.Service;

@Service
public interface ReadJwcMsgService {
    ReadJwcMsg findDistinctByStudentidAndMsgid(String studentid, int msgid);
    ReadJwcMsg findDistinctByTutoridAndMsgid(String tutorid, int msgid);
    ReadJwcMsg saveOrUpdate(ReadJwcMsg readJwcMsg);
}
