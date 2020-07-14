package com.ss.ssproj.dao;

import com.ss.ssproj.entity.JwcMessage;

import java.util.List;

public interface JwcMessageDao {
    JwcMessage findByJwcId(int id);
    List<JwcMessage> findAll();
}
