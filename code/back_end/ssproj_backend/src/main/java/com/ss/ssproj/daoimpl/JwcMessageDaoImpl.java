package com.ss.ssproj.daoimpl;

import com.ss.ssproj.dao.JwcMessageDao;
import com.ss.ssproj.entity.JwcMessage;
import com.ss.ssproj.repository.JwcMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JwcMessageDaoImpl implements JwcMessageDao {
    @Autowired
    JwcMessageRepository jwcMessageRepository;

    @Override
    public JwcMessage findByJwcId(int id) {
        JwcMessage ret = jwcMessageRepository.findDistinctById(id);
        ret.setIfRead(3);
        return ret;
    }

    @Override
    public List<JwcMessage> findAll() {
        return jwcMessageRepository.findAll();
    }


}
