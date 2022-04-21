package com.bus.dao;

import com.bus.entity.Customer;
import com.bus.util.Page;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface CustomerDao {
    //增
    int insertCustomer(Connection con, Customer cus)throws SQLException;

    //一次性增加多条记录
    int[] insertMore(Connection con, Customer... cus)throws SQLException;

    //删
    int deleteCustomerById(Connection con,Integer id)throws SQLException;

    //改
    int updateCustomerById(Connection con,Customer cus)throws SQLException;

    //查
    ResultSet selectAllCustomers(Connection con)throws SQLException;

    //查
    ResultSet selectByPage(Connection con, Page<Customer> page)throws SQLException;

    int getTotalRecord(Connection con)throws SQLException;

    int getSearchTotalRecord(Connection con,String name)throws SQLException;

    //查
    ResultSet selectCustomerById(Connection con,Integer id)throws SQLException;

    //查
    ResultSet selectCustomerByCondition(Connection con, String name,Page<Customer> page)throws SQLException;


    ResultSet selectAllCustomerName(Connection con,String customer_name)throws SQLException;
}
