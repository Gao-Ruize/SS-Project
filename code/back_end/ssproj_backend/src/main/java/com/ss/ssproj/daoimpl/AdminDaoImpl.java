package com.ss.ssproj.daoimpl;

import com.ss.ssproj.dao.AdminDao;
import com.ss.ssproj.entity.Admin;
import com.ss.ssproj.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDaoImpl implements AdminDao {
    @Autowired
    AdminRepository adminRepository;

    @Override
    public Admin findDistinctByUserid(String userid) {
        return this.adminRepository.findDistinctByUserid(userid);
    }

    @Override
    public Admin save(Admin admin) {
        return this.adminRepository.save(admin);
    }
}
