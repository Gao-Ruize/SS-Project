package com.ss.ssproj.repository;

import com.ss.ssproj.entity.Instruct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstructRepository extends JpaRepository<Instruct, Integer> {
    List<Instruct> findAll();
}
