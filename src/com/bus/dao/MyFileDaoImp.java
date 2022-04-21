package com.bus.dao;

import com.bus.entity.MyFile;
import com.bus.util.Page;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyFileDaoImp extends BaseDao implements MyFileDao {
    @Override
    public int insertFile(Connection con, MyFile file) throws SQLException {
        String sql = "insert into file(fileName, autoName, fileDesc, userName) values(?, ?, ?, ?)";
        Object[] obj = {file.getFileName(), file.getAutoName(), file.getFileDesc(), file.getUserName()};
        return super.insert(con, sql, obj);
    }

    @Override
    public int[] insertMore(Connection con, MyFile... file) throws SQLException {
        return new int[0];
    }

    @Override
    public int deleteFileById(Connection con, Integer id) throws SQLException {
        String sql = " delete from file where id = ? ";
        return super.delete(con, sql, id);
    }

    @Override
    public int updateFileById(Connection con, MyFile file) throws SQLException {
        String sql = "update file set  fileName = ? ,autoName = ? ,fileDesc = ? ,userName = ? where id = ? ";
        Object[] obj = {file.getFileName(), file.getAutoName(), file.getFileDesc(), file.getUserName(), file.getId()};
        return super.update(con, sql, obj);
    }

    @Override
    public ResultSet selectAllFiles(Connection con) throws SQLException {
        String sql = "select * from file";
        return super.select(con, sql);
    }

    @Override
    public ResultSet selectFileById(Connection con, Integer id) throws SQLException {
        String sql = "select * from file where id = ? ";
        return super.select(con, sql, id);
    }

    @Override
    public ResultSet selectFileByUserName(Connection con, String userName) throws SQLException {
        String sql = "select * from file where userName = ? ";

        return super.select(con, sql, userName);
    }

    @Override
    public ResultSet selectFileByFileName(Connection con, String fileName) throws SQLException {
        String sql = "select * from file where fileName = ? ";

        return super.select(con, sql, fileName);
    }

    @Override
    public ResultSet selectByPage(Connection con, Page<MyFile> page) throws SQLException {
        String sql = "select * from file order by id limit ?,? ";
        Object[] obj = {(page.getCurrentPage() - 1) * page.getPageSize(), page.getPageSize()};
        return super.select(con, sql, obj);
    }

    @Override
    public int getTotalRecord(Connection con) throws SQLException {
        String sql = "select count(id) from file";
        ResultSet set = super.select(con, sql);
        if (set.next()) {
            return set.getInt(1);
        }
        return 0;
    }
}
