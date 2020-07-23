package com.ss.ssproj.serviceimpl;

import com.ss.ssproj.dao.AdminDao;
import com.ss.ssproj.entity.Admin;
import com.ss.ssproj.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminDao adminDao;

    @Override
    public Admin findDistinctByUserid(String userid) {
        return this.adminDao.findDistinctByUserid(userid);
    }

    @Override
    public Admin save(Admin admin) {
        return this.adminDao.save(admin);
    }
}
