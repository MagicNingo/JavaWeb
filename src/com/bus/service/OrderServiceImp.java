package com.bus.service;

import com.bus.dao.OrderDaoImp;
import com.bus.entity.*;
import com.bus.util.DBHelper;
import com.bus.util.Page;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImp implements OrderService{

    private OrderDaoImp odi;

    public OrderServiceImp(){
        odi = new OrderDaoImp();
    }

    @Override
    public int addOrder(Order ord) {
        int i;
        Connection con = DBHelper.getConnection();
        try {
            con.setAutoCommit(false);
            i = odi.insertOrder(con,ord);
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
            odi.closeAll();
        }
        return 0;
    }

    @Override
    public List<Order> findAllOrder() {
        Connection con = DBHelper.getConnection();
        ArrayList<Order> list = new ArrayList<>();
        try {
            ResultSet set = odi.selectAllOrders(con);
            while(set.next()) {
                String orderID = set.getString("orderID");
                Timestamp hire_date = set.getTimestamp("order_date");
                int customerID = set.getInt("customerID");
                int empID = set.getInt("empID");

                list.add(new Order(orderID,hire_date, new Customer(customerID),new Employee(empID)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            odi.closeAll();
        }
        return list;
    }

    @Override
    public Page<Order> findOrderByPage(Page<Order> page) {
        ArrayList<Order> list = new ArrayList<>();
        Connection con = DBHelper.getConnection();
        try {
            ResultSet set = odi.selectByPage(con, page);
            int totalRecord = odi.getTotalRecord(con);
            while (set.next()) {
                String orderID = set.getString("orderID");
                Timestamp hire_date = set.getTimestamp("order_date");
                int customerID = set.getInt("customerID");
                int empID = set.getInt("empID");

                list.add(new Order(orderID,hire_date, new Customer(customerID),new Employee(empID)));
            }

            page.setList(list);
            page.setTotalRecord(totalRecord);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            odi.closeAll();
        }
        return page;
    }

    @Override
    public int removeOrderByID(String id) {
        Connection con = DBHelper.getConnection();
        try {
            odi.deleteOrderById(con, id);
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            odi.closeAll();
        }
        return 0;
    }

    @Override
    public int updateOrderByID(Order ord) {
        Connection con = DBHelper.getConnection();
        try {
            int i = odi.updateOrderById(con, ord);
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            odi.closeAll();
        }
        return 0;
    }

    @Override
    public Order findOrderByID(String id) {
        Connection con = DBHelper.getConnection();
        try {
            ResultSet set = odi.selectOrderById(con, id);
            while (set.next()) {
                String orderID = set.getString("orderID");
                Timestamp hire_date = set.getTimestamp("order_date");
                int customerID = set.getInt("customerID");
                int empID = set.getInt("empID");
                return new Order(orderID, hire_date, new Customer(customerID), new Employee(empID));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            odi.closeAll();
        }
        return null;
    }
}
