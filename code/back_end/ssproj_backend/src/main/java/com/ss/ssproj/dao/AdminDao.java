package com.ss.ssproj.dao;

import com.ss.ssproj.entity.Admin;

public interface AdminDao {
    Admin findDistinctByUserid(String userid);
    Admin save(Admin admin);
}
