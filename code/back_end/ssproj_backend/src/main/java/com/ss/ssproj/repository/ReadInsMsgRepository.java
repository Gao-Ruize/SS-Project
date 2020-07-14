package com.ss.ssproj.repository;

import com.ss.ssproj.entity.ReadInsMsg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReadInsMsgRepository extends JpaRepository<ReadInsMsg, Integer> {
    ReadInsMsg findDistinctByStudentidAndMsgid(String studentid, int msgid);
    ReadInsMsg save(ReadInsMsg readInsMsg);
    List<ReadInsMsg> findAllByStudentid(String studentid);
    List<ReadInsMsg> findAllByMsgid(int msgid);
}
