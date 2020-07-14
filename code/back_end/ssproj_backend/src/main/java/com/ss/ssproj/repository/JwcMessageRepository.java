package com.ss.ssproj.repository;

import com.ss.ssproj.entity.JwcMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JwcMessageRepository extends JpaRepository<JwcMessage, Integer> {
    JwcMessage findDistinctById(int jwcMsgId);
    List<JwcMessage> findAll();
}
