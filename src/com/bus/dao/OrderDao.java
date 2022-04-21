package com.bus.dao;

import com.bus.entity.Order;
import com.bus.util.Page;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface OrderDao {
    //增
    int insertOrder(Connection con, Order cus)throws SQLException;

    //一次性增加多条记录
    int[] insertMore(Connection con, Order... cus)throws SQLException;

    //删
    int deleteOrderById(Connection con,String id)throws SQLException;

    //改
    int updateOrderById(Connection con,Order cus)throws SQLException;

    //查
    ResultSet selectAllOrders(Connection con)throws SQLException;

    //查
    ResultSet selectByPage(Connection con, Page<Order> page)throws SQLException;

    int getTotalRecord(Connection con)throws SQLException;

    int getSearchTotalRecord(Connection con,String name)throws SQLException;

    //查
    ResultSet selectOrderById(Connection con,String id)throws SQLException;

    //查
    ResultSet selectOrderByCondition(Connection con, String name,Page<Order> page)throws SQLException;


}
