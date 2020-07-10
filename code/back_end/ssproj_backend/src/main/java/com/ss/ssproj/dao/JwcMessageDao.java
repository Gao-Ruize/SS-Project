package com.ss.ssproj.dao;

import com.ss.ssproj.entity.JwcMessage;

public interface JwcMessageDao {
    JwcMessage findByJwcId(int id);
}
