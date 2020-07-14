package com.ss.ssproj.repository;

import com.ss.ssproj.entity.JwcMessage;
import com.ss.ssproj.entity.ReadJwcMsg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadJwcMsgRepository extends JpaRepository<ReadJwcMsg, Integer> {
    ReadJwcMsg findDistinctByStudentidAndMsgid(String studentid, int msgid);
    ReadJwcMsg findDistinctByTutoridAndMsgid(String tutorid, int msgid);
    ReadJwcMsg save(ReadJwcMsg readJwcMsg);
}
