package com.bus.dao;

import com.bus.entity.MyFile;
import com.bus.util.Page;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface MyFileDao {
    //增
    int insertFile(Connection con, MyFile file) throws SQLException;

    //一次性增加多条记录
    int[] insertMore(Connection con, MyFile... file) throws SQLException;

    //删
    int deleteFileById(Connection con, Integer id) throws SQLException;

    //改
    int updateFileById(Connection con, MyFile file) throws SQLException;

    //查
    ResultSet selectAllFiles(Connection con) throws SQLException;

    ResultSet selectFileById(Connection con,Integer id) throws SQLException;

    ResultSet selectFileByUserName(Connection con,String userName) throws SQLException;

    ResultSet selectFileByFileName(Connection con,String fileName) throws SQLException;

    ResultSet selectByPage(Connection con, Page<MyFile> page)throws SQLException;

    int getTotalRecord(Connection con)throws SQLException;


}
