package com.ss.ssproj.repository;

import com.ss.ssproj.entity.JwcMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JwcMessageRepository extends JpaRepository<JwcMessage, Integer> {
    JwcMessage findDistinctByJwcMsgId(int jwcMsgId);
}
