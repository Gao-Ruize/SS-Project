package com.ss.ssproj.serviceimpl;

import com.ss.ssproj.dao.JwcMessageDao;
import com.ss.ssproj.dao.ReadJwcMsgDao;
import com.ss.ssproj.entity.JwcMessage;
import com.ss.ssproj.entity.ReadJwcMsg;
import com.ss.ssproj.service.ReadJwcMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadJwcMsgServiceImpl implements ReadJwcMsgService {
    @Autowired
    ReadJwcMsgDao readJwcMsgDao;

    //每次进入页面都需要加载教务处信息
    //加入cache避免频繁的搜索数据库
    //学生id与教师id没有交集
    @Cacheable("sReadJwcMsg")
    @Override
    public ReadJwcMsg findDistinctByStudentidAndMsgid(String studentid, int msgid) {
        return readJwcMsgDao.findDistinctByStudentidAndMsgid(studentid, msgid);
    }

    @Cacheable("tReadJwcMsg")
    @Override
    public ReadJwcMsg findDistinctByTutoridAndMsgid(String tutorid, int msgid) {
        return readJwcMsgDao.findDistinctByTutoridAndMsgid(tutorid, msgid);
    }

    @Override
    public ReadJwcMsg saveOrUpdate(ReadJwcMsg readJwcMsg) {
        return readJwcMsgDao.save(readJwcMsg);
    }

    @Override
    public List<ReadJwcMsg> findAllByStudentidAndIfread(String studentid, int ifread) {
        return readJwcMsgDao.findAllByStudentidAndIfread(studentid, ifread);
    }

    @Override
    public List<ReadJwcMsg> findAllByTutoridAndIfread(String tutorid, int ifread) {
        return readJwcMsgDao.findAllByTutoridAndIfread(tutorid, ifread);
    }

    @Override
    public List<ReadJwcMsg> findAllByIfstudentAndIfreadAndMsgid(int ifstudent, int ifread, int msgid) {
        return readJwcMsgDao.findAllByIfstudentAndIfreadAndMsgid(ifstudent, ifread, msgid);
    }


}
