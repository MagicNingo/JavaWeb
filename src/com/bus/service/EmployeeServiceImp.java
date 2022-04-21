package com.bus.service;

import com.bus.dao.EmployeeDaoImp;
import com.bus.entity.*;
import com.bus.util.DBHelper;
import com.bus.util.Page;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceImp implements EmployeeService{

    private EmployeeDaoImp edi;

    public EmployeeServiceImp(){
        edi = new EmployeeDaoImp();
    }

    @Override
    public int addEmployee(Employee emp) {
        int i;
        Connection con = DBHelper.getConnection();
        try {
            con.setAutoCommit(false);
            i = edi.insertEmployee(con,emp);
            con.commit();
            con.setAutoCommit(true);
            return i;

        } catch (SQLException e) {
            try {
                con.rollback();
                con.setAutoCommit(true);
            } catch (SQLException w) {
                w.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            edi.closeAll();
        }
        return 0;
    }

    @Override
    public List<Employee> findAllEmployee() {
        Connection con = DBHelper.getConnection();
        ArrayList<Employee> list = new ArrayList<>();
        try {
            ResultSet set = edi.selectAllEmployees(con);
            while(set.next()) {
                int empID = set.getInt("empID");
                String emp_name = set.getString("emp_name");
                Timestamp hire_date = set.getTimestamp("hire_date");
                Double salary = set.getDouble("salary");
                list.add(new Employee(empID,emp_name,hire_date,salary));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            edi.closeAll();
        }
        return list;
    }

    @Override
    public Page<Employee> findEmployeeByPage(Page<Employee> page) {
        ArrayList<Employee> list = new ArrayList<>();
        Connection con = DBHelper.getConnection();
        try {
            ResultSet set = edi.selectByPage(con, page);
            int totalRecord = edi.getTotalRecord(con);
            while (set.next()) {
                int empID = set.getInt("empID");
                String emp_name = set.getString("emp_name");
                Timestamp hire_date = set.getTimestamp("hire_date");
                Double salary = set.getDouble("salary");

                list.add(new Employee(empID,emp_name,hire_date,salary));
            }

            page.setList(list);
            page.setTotalRecord(totalRecord);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            edi.closeAll();
        }
        return page;
    }

    @Override
    public Page<Employee> findEmployeeByCondition(String name, Page<Employee> page) {
        ArrayList<Employee> list = new ArrayList<>();
        Connection con = DBHelper.getConnection();
        try {
            ResultSet set = edi.selectEmployeeByCondition(con, name, page);
            int totalRecord = edi.getSearchTotalRecord(con, name);
            while (set.next()) {
                int empID = set.getInt("empID");
                String emp_name = set.getString("emp_name");
                Timestamp hire_date = set.getTimestamp("hire_date");
                Double salary = set.getDouble("salary");

                list.add(new Employee(empID,emp_name,hire_date,salary));
            }

            page.setList(list);
            page.setTotalRecord(totalRecord);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            edi.closeAll();
        }
        return page;
    }

    @Override
    public int removeEmployeeByID(Integer id) {
        Connection con = DBHelper.getConnection();
        try {
            edi.deleteEmployeeById(con, id);
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            edi.closeAll();
        }
        return 0;
    }

    @Override
    public int updateEmployeeByID(Employee emp) {
        Connection con = DBHelper.getConnection();
        try {
            int i = edi.updateEmployeeById(con, emp);
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            edi.closeAll();
        }
        return 0;
    }

    @Override
    public Employee findEmployeeByID(Integer id) {
        Connection con = DBHelper.getConnection();
        try {
            ResultSet set = edi.selectEmployeeById(con, id);
            while (set.next()) {
                int empID = set.getInt("empID");
                String emp_name = set.getString("emp_name");
                Timestamp hire_date = set.getTimestamp("hire_date");
                Double salary = set.getDouble("salary");
                return new Employee(empID, emp_name, hire_date, salary);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            edi.closeAll();
        }
        return null;
    }

    @Override
    public int findAllEmployeeName(String emp_name) {
        return 0;
    }
}
