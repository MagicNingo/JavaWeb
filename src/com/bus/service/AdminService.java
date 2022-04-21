package com.bus.service;

import com.bus.entity.Admin;

import java.util.List;

public interface AdminService {
    int addAdmin(Admin admin);

    int findAdminByLogin(Admin admin);

    List<Admin> findAllAdmins();

    int deleteAdminByID(int id);
}
