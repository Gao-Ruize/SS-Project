package com.ss.ssproj.daoimpl;

import com.ss.ssproj.dao.ReadJwcMsgDao;
import com.ss.ssproj.entity.ReadJwcMsg;
import com.ss.ssproj.repository.ReadJwcMsgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReadJwcMsgDaoImpl implements ReadJwcMsgDao {
    @Autowired
    ReadJwcMsgRepository readJwcMsgRepository;

    @Override
    public ReadJwcMsg findDistinctByStudentidAndMsgid(String studentid, int msgid) {
        return readJwcMsgRepository.findDistinctByStudentidAndMsgid(studentid, msgid);
    }

    @Override
    public ReadJwcMsg findDistinctByTutoridAndMsgid(String tutorid, int msgid) {
        return readJwcMsgRepository.findDistinctByTutoridAndMsgid(tutorid, msgid);
    }

    @Override
    public ReadJwcMsg save(ReadJwcMsg readJwcMsg) {
        return readJwcMsgRepository.save(readJwcMsg);
    }

    @Override
    public List<ReadJwcMsg> findAllByStudentidAndIfread(String studentid, int ifread) {
        return readJwcMsgRepository.findAllByStudentidAndIfread(studentid, ifread);
    }

    @Override
    public List<ReadJwcMsg> findAllByTutoridAndIfread(String tutorid, int ifread) {
        return readJwcMsgRepository.findAllByTutoridAndIfread(tutorid, ifread);
    }

    @Override
    public List<ReadJwcMsg> findAllByIfstudentAndIfreadAndMsgid(int ifstudent, int ifread, int msgid) {
        return readJwcMsgRepository.findAllByIfstudentAndIfreadAndMsgid(ifstudent, ifread, msgid);
    }

}
