package com.ss.ssproj.service;

import com.ss.ssproj.dao.AdminDao;
import com.ss.ssproj.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {
    Admin findDistinctByUserid(String userid);
    Admin save(Admin admin);
}
