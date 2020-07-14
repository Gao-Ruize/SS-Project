package com.ss.ssproj.dao;

import com.ss.ssproj.entity.Instruct;
import com.ss.ssproj.repository.InstructRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface InstructDao {
    List<Instruct> findAll();
    Instruct save(Instruct instruct);

}
