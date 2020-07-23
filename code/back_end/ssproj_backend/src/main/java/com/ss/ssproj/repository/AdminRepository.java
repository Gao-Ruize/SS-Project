package com.ss.ssproj.repository;

import com.ss.ssproj.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findDistinctByUserid(String userid);
    Admin save(Admin admin);
}
