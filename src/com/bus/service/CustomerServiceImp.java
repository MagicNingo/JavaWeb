package com.bus.service;

import com.bus.dao.CustomerDaoImp;
import com.bus.entity.*;
import com.bus.util.DBHelper;
import com.bus.util.Page;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImp implements CustomerService{
    private CustomerDaoImp cdi;
    public CustomerServiceImp(){
        cdi = new CustomerDaoImp();
    }

    @Override
    public int addCustomer(Customer customer) {
        int i = 0;
        Connection con = DBHelper.getConnection();
        try {
            con.setAutoCommit(false);
            i = cdi.insertCustomer(con,customer);
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
            cdi.closeAll();
        }
        return 0;
    }

    @Override
    public List<Customer> findAllCustomers() {
        Connection con = DBHelper.getConnection();
        ArrayList<Customer> list = new ArrayList<>();
        try {
            ResultSet set = cdi.selectAllCustomers(con);
            while(set.next()) {
                int customerID = set.getInt("customerID");
                String customer_name = set.getString("customer_name");
                String customer_add = set.getString("customer_add");
                String customer_bir = set.getString("customer_bir");
                String customer_tel = set.getString("customer_tel");
                list.add(new Customer(customerID,customer_name,customer_add,customer_bir,customer_tel));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cdi.closeAll();
        }
        return list;
    }

    @Override
    public Page<Customer> findCustomerByPage(Page<Customer> page) {
        ArrayList<Customer> list = new ArrayList<>();
        Connection con = DBHelper.getConnection();
        try {
            ResultSet set = cdi.selectByPage(con, page);
            int totalRecord = cdi.getTotalRecord(con);
            while (set.next()) {
                int customerID = set.getInt("customerID");
                String customer_name = set.getString("customer_name");
                String customer_add = set.getString("customer_add");
                String customer_bir = set.getString("customer_bir");
                String customer_tel = set.getString("customer_tel");

                list.add(new Customer(customerID, customer_name, customer_add,
                        customer_bir, customer_tel));
            }

            page.setList(list);
            page.setTotalRecord(totalRecord);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cdi.closeAll();
        }
        return page;
    }

    @Override
    public Page<Customer> findCustomerByCondition(String name, Page<Customer> page) {
        return null;
    }

    @Override
    public int removeCustomerByID(Integer id) {
        Connection con = DBHelper.getConnection();
        try {
            cdi.deleteCustomerById(con, id);
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cdi.closeAll();
        }
        return 0;
    }

    @Override
    public int updateProductByID(Customer customer) {
        Connection con = DBHelper.getConnection();
        try {
            int i = cdi.updateCustomerById(con, customer);
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cdi.closeAll();
        }
        return 0;
    }

    @Override
    public Customer findCustomerByID(Integer id) {
        Connection con = DBHelper.getConnection();
        try {
            ResultSet set = cdi.selectCustomerById(con, id);
            while (set.next()) {
                int customerID = set.getInt("customerID");
                String customer_name = set.getString("customer_name");
                String customer_add = set.getString("customer_add");
                String customer_bir = set.getString("customer_bir");
                String customer_tel = set.getString("customer_tel");
                return new Customer(customerID, customer_name, customer_add,
                        customer_bir, customer_tel);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cdi.closeAll();
        }
        return null;
    }

    @Override
    public int findAllCustomerName(String name) {
        return 0;
    }
}
