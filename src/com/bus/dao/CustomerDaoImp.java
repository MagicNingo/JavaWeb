package com.bus.dao;

import com.bus.entity.Customer;
import com.bus.util.Page;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDaoImp extends BaseDao implements CustomerDao{
    @Override
    public int insertCustomer(Connection con, Customer cus) throws SQLException {
        String sql = "insert into customer(customer_name, customer_add, " +
                "customer_bir, customer_tel) value(?, ?, ?, ?) ";
        Object[] obj = {cus.getCustomer_name(), cus.getCustomer_add(),
                cus.getCustomer_bir(), cus.getCustomer_tel()};

        return super.insert(con, sql, obj);
    }

    @Override
    public int[] insertMore(Connection con, Customer... cus) throws SQLException {
        return new int[0];
    }

    @Override
    public int deleteCustomerById(Connection con, Integer id) throws SQLException {
        String sql = " delete from customer where customerID = ? ";
        return super.delete(con,sql,id);
    }

    @Override
    public int updateCustomerById(Connection con, Customer cus) throws SQLException {
        String sql = " update customer set customer_name =?,  customer_add =?, " +
                " customer_bir =?, customer_tel =? where customerID =? ";
        Object[] obj = {cus.getCustomer_name(), cus.getCustomer_add(),
                cus.getCustomer_bir(), cus.getCustomer_tel(), cus.getCustomerID()};
        return super.update(con,sql,obj);
    }

    @Override
    public ResultSet selectAllCustomers(Connection con) throws SQLException {
        String sql = " select * from customer ";
        return super.select(con,sql);
    }

    @Override
    public ResultSet selectByPage(Connection con, Page<Customer> page) throws SQLException {
        String sql =  " select * from customer " +
                " order by customerID limit ?,? ";
        Object[] obj = {(page.getCurrentPage()-1)*page.getPageSize(),page.getPageSize()};
        return super.select(con,sql,obj);
    }

    @Override
    public int getTotalRecord(Connection con) throws SQLException {
        String sql = "select count(customerID) from customer";//前端页面未带搜索功能的查询总页数的sql语句
        ResultSet set = super.select(con, sql);
        if (set.next()){
            return set.getInt(1);
        }

        return 0;
    }

    @Override
    public int getSearchTotalRecord(Connection con, String name) throws SQLException {
        String sql = "select count(customerID) from customer where customer_name like  concat('%',?,'%')";
        ResultSet set = super.select(con, sql ,name);
        if (set.next()){
            return set.getInt(1);
        }
        return 0;
    }

    @Override
    public ResultSet selectCustomerById(Connection con, Integer id) throws SQLException {
        String sql ="select * from customer where customerID = ? ";
        return super.select(con,sql,id);
    }

    @Override
    public ResultSet selectCustomerByCondition(Connection con, String name, Page<Customer> page) throws SQLException {
        String sql = "select * from customer where customer_name like  concat('%',?,'%') " +
                " order by customerID limit ?,? ";
        Object[] obj = {name,(page.getCurrentPage()-1)*page.getPageSize(),page.getPageSize()};
        return super.select(con,sql,obj);
    }

    @Override
    public ResultSet selectAllCustomerName(Connection con, String customer_name) throws SQLException {
        String sql = "select customer_name from category where customer_name=?";
        return super.select(con,sql,customer_name);
    }
}
