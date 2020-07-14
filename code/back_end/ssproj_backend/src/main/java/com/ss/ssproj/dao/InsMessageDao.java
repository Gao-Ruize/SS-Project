package com.ss.ssproj.dao;

import com.ss.ssproj.entity.InsMessage;

import java.util.List;

public interface InsMessageDao {
   InsMessage findDistinctById(int id);
   InsMessage save(InsMessage insMessage);
}
