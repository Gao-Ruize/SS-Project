package com.ss.ssproj.daoimpl;

import com.ss.ssproj.dao.InsMessageDao;
import com.ss.ssproj.entity.InsMessage;
import com.ss.ssproj.repository.InsMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InsMessageDaoImpl implements InsMessageDao {
    @Autowired
    InsMessageRepository insMessageRepository;

    @Override
    public InsMessage findDistinctById(int id) {
        return insMessageRepository.findDistinctById(id);
    }

    @Override
    public InsMessage save(InsMessage insMessage) {
        return insMessageRepository.save(insMessage);
    }

}
