package com.bus.dao;

import com.bus.entity.Admin;
import com.bus.util.TimeUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class AdminDaoImp extends BaseDao implements AdminDao{


    @Override
    public ResultSet selectByLogin(Connection con,Admin admin) throws SQLException {
        String sql = "select admin_password from admin where admin_name=?";

        return super.select(con,sql,admin.getAdmin_name());
    }

    @Override
    public int insertAdmin(Connection con, Admin admin) throws SQLException {
        String sql = "insert into admin(admin_name,admin_password,date) values(?,?,?)";
        //Oracle数据库
        /*String sql = "insert into admin(admin_id,admin_name,admin_password,date) values(admin_seq.nextval,?,?,?)";*/
        Object[] obj={admin.getAdmin_name(),admin.getAdmin_password(), TimeUtil.dateToString(admin.getDate(),"yyyy-MM-dd")};
        return super.insert(con,sql,obj);
    }

    @Override
    public int[] insertMore(Connection con, Admin... admin) throws SQLException {
        String sql = "insert into admin(admin_name,admin_password) values(?,?)";
        Object[][] obj = new Object[admin.length][];
        for (int i = 0; i < obj.length; i++) {
            obj[i]=new Object[]{admin[i].getAdmin_name(),admin[i].getAdmin_password()};
        }
        return super.insertMore(con,sql,obj);
    }

    @Override
    public int deleteAdminById(Connection con, Integer id) throws SQLException {
        String sql = "delete from admin where admin_id = ?";

        return super.delete(con,sql,id);
    }

    @Override
    public int updateAdminById(Connection con,Admin admin) throws SQLException {
        String sql = "update admin set admin_password=?  ";
        return super.update(con,sql,admin.getAdmin_id());
    }

    @Override
    public ResultSet selectAllAdmins(Connection con) throws SQLException {
        String sql = "select * from admin";
        return super.select(con,sql);
    }


    @Override
    public ResultSet selectAdminById(Connection con, Integer id) throws SQLException {
        String sql = "select * from admin where admin_id=?";
        return super.select(con,sql,id);
    }

    @Override
    public ResultSet selectAdminByCondition(Connection con, Admin admin) throws SQLException {
        return null;
    }

}
