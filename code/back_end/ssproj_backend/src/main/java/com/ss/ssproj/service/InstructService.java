package com.ss.ssproj.service;

import com.ss.ssproj.entity.Instruct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InstructService {
    List<Instruct> findAll();
    Instruct saveOrUpdate(Instruct instruct);
    List<Instruct> findAllByTutorid(String tutorid);
    Instruct findDistinctByStudentidAndTutorid(String studentid, String tutorid);
    Instruct findDistinctByStudentid(String studentid);
    void deleteById(int id);
}
