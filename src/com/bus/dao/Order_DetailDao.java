package com.bus.dao;

import com.bus.entity.Order_Detail;
import com.bus.util.Page;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface Order_DetailDao {
    //增
    int insertOrder_Detail(Connection con, Order_Detail od) throws SQLException;

    //一次性增加多条记录
    int[] insertMore(Connection con, Order_Detail... od) throws SQLException;

    //删
    int deleteOrder_DetailById(Connection con, Integer id) throws SQLException;

    //改
    int updateOrder_DetailById(Connection con, Order_Detail od) throws SQLException;

    //查
    ResultSet selectAllOrder_Details(Connection con) throws SQLException;

    //查
    ResultSet selectByPage(Connection con, Page<Order_Detail> page) throws SQLException;

    int getTotalRecord(Connection con) throws SQLException;

    int getSearchTotalRecord(Connection con, Integer id) throws SQLException;

    //查
    ResultSet selectOrder_DetailById(Connection con, Integer id) throws SQLException;

    //查
    ResultSet selectOrder_DetailByCondition(Connection con, String name, Page<Order_Detail> page) throws SQLException;

}
