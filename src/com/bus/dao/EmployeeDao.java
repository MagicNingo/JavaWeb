package com.bus.dao;

import com.bus.entity.Employee;
import com.bus.util.Page;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface EmployeeDao {
    //增
    int insertEmployee(Connection con, Employee emp)throws SQLException;

    //一次性增加多条记录
    int[] insertMore(Connection con, Employee... emp)throws SQLException;

    //删
    int deleteEmployeeById(Connection con,Integer id)throws SQLException;

    //改
    int updateEmployeeById(Connection con,Employee emp)throws SQLException;

    //查
    ResultSet selectAllEmployees(Connection con)throws SQLException;

    //查
    ResultSet selectByPage(Connection con, Page<Employee> page)throws SQLException;

    int getTotalRecord(Connection con)throws SQLException;

    int getSearchTotalRecord(Connection con,String name)throws SQLException;

    //查
    ResultSet selectEmployeeById(Connection con,Integer id)throws SQLException;

    //查
    ResultSet selectEmployeeByCondition(Connection con, String name,Page<Employee> page)throws SQLException;


    ResultSet selectAllEmployeeName(Connection con,String employee_name)throws SQLException;

}
