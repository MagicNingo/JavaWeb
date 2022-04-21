package com.bus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDao {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet set;

    public int  insert(Connection con,String sql,Object... obj) throws SQLException {
        this.con= con;
        ps = con.prepareStatement(sql);
        //给问号赋值
        for (int i = 0; i < obj.length; i++) {
            ps.setObject(i+1,obj[i]);
        }
        return ps.executeUpdate();
    }

    //一次性增加多条记录
    public int[]  insertMore(Connection con, String sql, Object[][] obj) throws SQLException {
        this.con= con;
        ps = con.prepareStatement(sql);
        for (int i = 0; i < obj.length; i++) {
            for (int j = 0; j < obj[i].length; j++) {
                ps.setObject(j+1,obj[i][j]);
            }
            ps.addBatch();
        }
        return ps.executeBatch();
    }


    public int update(Connection con, String sql, Object...obj) throws SQLException {
       this.con= con;
        ps = con.prepareStatement(sql);
        //给问号赋值
        for (int i = 0; i < obj.length; i++) {
            ps.setObject(i+1,obj[i]);
        }
        return ps.executeUpdate();
    }

    public int delete(Connection con, String sql,Object...obj) throws SQLException {
        this.con= con;
        ps = con.prepareStatement(sql);
        for (int i = 0; i < obj.length; i++) {
            ps.setObject(i+1,obj[i]);
        }
        return ps.executeUpdate();
    }
//通用查询操作
    public ResultSet select(Connection con, String sql, Object...obj) throws SQLException {
        this.con= con;
        ps = con.prepareStatement(sql);
        //给问号赋值
        for (int i = 0; i < obj.length; i++) {
            ps.setObject(i+1,obj[i]);
        }
        return ps.executeQuery();
    }

//关闭资源
    public void closeAll() {
        try {
            if (set != null) {
                set.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("---close all---");

    }
}
