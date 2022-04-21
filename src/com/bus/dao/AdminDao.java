package com.bus.dao;

import com.bus.entity.Admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface AdminDao {
    //增
    int insertAdmin(Connection con, Admin admin)throws SQLException;

    //一次性增加多条记录
    int[] insertMore(Connection con, Admin... admin)throws SQLException;

    //删
    int deleteAdminById(Connection con,Integer id)throws SQLException;

    //改
    int updateAdminById(Connection con,Admin admin)throws SQLException;

    //查
    ResultSet selectAllAdmins(Connection con)throws SQLException;

    //查
    ResultSet selectByLogin(Connection con,Admin admin)throws SQLException;

    //查
    ResultSet selectAdminById(Connection con,Integer id)throws SQLException;

    //查
    //ResultSet selectByPage(Connection con)throws SQLException;
    //查
    ResultSet selectAdminByCondition(Connection con,Admin admin)throws SQLException;
}
