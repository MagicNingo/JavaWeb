package com.bus.dao;

import com.bus.entity.Order_Detail;
import com.bus.util.Page;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Order_DetailDaoImp extends BaseDao implements Order_DetailDao {
    @Override
    public int insertOrder_Detail(Connection con, Order_Detail od) throws SQLException {
        String sql = "insert into order_detail(orderID, productID, " +
                "quantity, discount) value(?, ?, ?, ?) ";
        Object[] obj = {od.getOrder().getOrderID(), od.getP().getProductID(), od.getQuantity(), od.getDiscount()};
        return super.insert(con, sql, obj);
    }

    @Override
    public int[] insertMore(Connection con, Order_Detail... od) throws SQLException {
        return new int[0];
    }

    @Override
    public int deleteOrder_DetailById(Connection con, Integer id) throws SQLException {
        String sql = " delete from order_detail where id = ? ";
        return super.delete(con, sql, id);
    }

    @Override
    public int updateOrder_DetailById(Connection con, Order_Detail od) throws SQLException {
        String sql = " update order_detail set orderID =?,  productID =?, " +
                " quantity =?, discount =? where id =? ";
        Object[] obj = {od.getOrder().getOrderID(), od.getP().getProductID(), od.getQuantity(), od.getDiscount()};
        return super.update(con, sql, obj);
    }

    @Override
    public ResultSet selectAllOrder_Details(Connection con) throws SQLException {
        String sql = " select * from order_detail ";
        return super.select(con,sql);
    }

    @Override
    public ResultSet selectByPage(Connection con, Page<Order_Detail> page) throws SQLException {
        String sql =  " select * from order_detail " +
                " order by id limit ?,? ";
        Object[] obj = {(page.getCurrentPage()-1)*page.getPageSize(),page.getPageSize()};
        return super.select(con,sql,obj);
    }

    @Override
    public int getTotalRecord(Connection con) throws SQLException {
        String sql = "select count(id) from order_detail";//前端页面未带搜索功能的查询总页数的sql语句
        ResultSet set = super.select(con, sql);
        if (set.next()){
            return set.getInt(1);
        }
        return 0;
    }

    @Override
    public int getSearchTotalRecord(Connection con, Integer id) throws SQLException {
        return 0;
    }

    @Override
    public ResultSet selectOrder_DetailById(Connection con, Integer id) throws SQLException {
        String sql ="select * from order_detail where id = ? ";
        return super.select(con,sql,id);
    }

    @Override
    public ResultSet selectOrder_DetailByCondition(Connection con, String name, Page<Order_Detail> page) throws SQLException {
        return null;
    }
}
