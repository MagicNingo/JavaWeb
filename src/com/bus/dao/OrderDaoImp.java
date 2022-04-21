package com.bus.dao;

import com.bus.entity.Order;
import com.bus.util.Page;
import com.bus.util.TimeUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDaoImp extends BaseDao implements OrderDao{
    @Override
    public int insertOrder(Connection con, Order ord) throws SQLException {
        String sql = "insert into order(orderID, order_date, " +
                "customerID, empID) value(?, ?, ?, ?) ";
        Object[] obj = {ord.getOrderID(), TimeUtil.dateToString(ord.getOrder_date(), "yyyy-MM-dd HH:mm:ss"),
                ord.getCus().getCustomerID(), ord.getEmp().getEmpID()};

        return super.insert(con, sql, obj);
    }

    @Override
    public int[] insertMore(Connection con, Order... ord) throws SQLException {
        return new int[0];
    }

    @Override
    public int deleteOrderById(Connection con, String id) throws SQLException {
        String sql = " delete from order where orderID = ? ";
        return super.delete(con,sql,id);
    }

    @Override
    public int updateOrderById(Connection con, Order ord) throws SQLException {
        String sql = " update order set  order_date =?, " +
                " customerID =?, empID =? where orderID =? ";
        Object[] obj = {TimeUtil.dateToString(ord.getOrder_date(), "yyyy-MM-dd HH:mm:ss"),
                ord.getCus().getCustomerID(), ord.getEmp().getEmpID(),ord.getOrderID()};
        return super.update(con,sql,obj);
    }

    @Override
    public ResultSet selectAllOrders(Connection con) throws SQLException {
        String sql = " select * from order ";
        return super.select(con,sql);
    }

    @Override
    public ResultSet selectByPage(Connection con, Page<Order> page) throws SQLException {

        String sql =  " select * from order order by orderID limit ?,? ";
        Object[] obj = {(page.getCurrentPage()-1)*page.getPageSize(),page.getPageSize()};
        return super.select(con,sql,obj);
    }

    @Override
    public int getTotalRecord(Connection con) throws SQLException {
        String sql = "select count(orderID) from order";//前端页面未带搜索功能的查询总页数的sql语句
        ResultSet set = super.select(con, sql);
        if (set.next()){
            return set.getInt(1);
        }

        return 0;
    }

    @Override
    public int getSearchTotalRecord(Connection con, String id) throws SQLException {
        String sql = "select count(orderID) from order where orderID = ? ";
        ResultSet set = super.select(con, sql ,id);
        if (set.next()){
            return set.getInt(1);
        }
        return 0;
    }

    @Override
    public ResultSet selectOrderById(Connection con, String id) throws SQLException {
        String sql ="select * from order where orderID = ? ";
        return super.select(con,sql,id);
    }

    @Override
    public ResultSet selectOrderByCondition(Connection con, String name, Page<Order> page) throws SQLException {
        String sql = "select * from order where orderID = ? " +
                " order by orderID limit ?,? ";
        Object[] obj = {name,(page.getCurrentPage()-1)*page.getPageSize(),page.getPageSize()};
        return super.select(con,sql,obj);
    }
}
