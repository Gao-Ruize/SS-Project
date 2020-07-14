package com.ss.ssproj.repository;

import com.ss.ssproj.entity.InsMessage;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InsMessageRepository extends JpaRepository<InsMessage,Integer> {
    public List<InsMessage> findAll(Sort sort);
    public InsMessage findDistinctById(int id);
    public InsMessage save(InsMessage insMessage);
}
