package com.bus.dao;

import com.bus.entity.Employee;
import com.bus.util.Page;
import com.bus.util.TimeUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDaoImp extends BaseDao implements EmployeeDao{
    @Override
    public int insertEmployee(Connection con, Employee emp) throws SQLException {
        String sql = "insert into employee(emp_name, hire_date, salary) value(?, ?, ?) ";
        Object[] obj = {emp.getEmp_name(), TimeUtil.dateToString(emp.getHire_date(), "yyyy-MM-dd HH:mm:ss"),
        emp.getSalary()};

        return super.insert(con, sql, obj);
    }

    @Override
    public int[] insertMore(Connection con, Employee... emp) throws SQLException {
        return new int[0];
    }

    @Override
    public int deleteEmployeeById(Connection con, Integer id) throws SQLException {
        String sql = " delete from employee where empID = ? ";
        return super.delete(con,sql,id);
    }

    @Override
    public int updateEmployeeById(Connection con, Employee emp) throws SQLException {
        String sql = " update employee set emp_name =? , hire_date =?, salary =? where empID =? ";
        Object[] obj = {emp.getEmp_name(), TimeUtil.dateToString(emp.getHire_date(), "yyyy-MM-dd HH:mm:ss"),
        emp.getSalary(), emp.getEmpID()};
        return super.update(con,sql,obj);
    }

    @Override
    public ResultSet selectAllEmployees(Connection con) throws SQLException {
        String sql = " select * from employee ";
        return super.select(con,sql);
    }

    @Override
    public ResultSet selectByPage(Connection con, Page<Employee> page) throws SQLException {
        String sql =  " select * from employee " +
                " order by empID limit ?,? ";
        Object[] obj = {(page.getCurrentPage()-1)*page.getPageSize(),page.getPageSize()};
        return super.select(con,sql,obj);
    }

    @Override
    public int getTotalRecord(Connection con) throws SQLException {
        String sql = "select count(empID) from employee";//前端页面未带搜索功能的查询总页数的sql语句
        ResultSet set = super.select(con, sql);
        if (set.next()){
            return set.getInt(1);
        }

        return 0;
    }

    @Override
    public int getSearchTotalRecord(Connection con, String name) throws SQLException {
        String sql = "select count(empID) from employee where emp_name like  concat('%',?,'%')";
        ResultSet set = super.select(con, sql ,name);
        if (set.next()){
            return set.getInt(1);
        }
        return 0;
    }

    @Override
    public ResultSet selectEmployeeById(Connection con, Integer id) throws SQLException {
        String sql ="select * from employee where empID = ? ";
        return super.select(con,sql,id);
    }

    @Override
    public ResultSet selectEmployeeByCondition(Connection con, String name, Page<Employee> page) throws SQLException {
        String sql = "select * from employee where emp_name like  concat('%',?,'%') " +
                " order by empID limit ?,? ";
        Object[] obj = {name,(page.getCurrentPage()-1)*page.getPageSize(),page.getPageSize()};
        return super.select(con,sql,obj);
    }

    @Override
    public ResultSet selectAllEmployeeName(Connection con, String employee_name) throws SQLException {
        String sql = "select emp_name from category where emp_name=?";
        return super.select(con,sql,employee_name);
    }
}
