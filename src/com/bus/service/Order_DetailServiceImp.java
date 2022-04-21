package com.bus.service;

import com.bus.dao.Order_DetailDaoImp;
import com.bus.entity.*;
import com.bus.util.DBHelper;
import com.bus.util.Page;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Order_DetailServiceImp implements Order_DetailService{

    private Order_DetailDaoImp odd;
    public Order_DetailServiceImp(){
        odd = new Order_DetailDaoImp();
    }
    @Override
    public int addOrder_Detail(Order_Detail od) {
        int i;
        Connection con = DBHelper.getConnection();
        try {
            con.setAutoCommit(false);
            i = odd.insertOrder_Detail(con,od);
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
            odd.closeAll();
        }
        return 0;
    }

    @Override
    public List<Order_Detail> findAllOrder_Detail() {
        Connection con = DBHelper.getConnection();
        ArrayList<Order_Detail> list = new ArrayList<>();
        try {
            ResultSet set = odd.selectAllOrder_Details(con);
            while(set.next()) {
                int id = set.getInt("id");
                String orderID = set.getString("orderID");
                int productID = set.getInt("productID");
                int quantity = set.getInt("quantity");
                double discount = set.getDouble("discount");
                list.add(new Order_Detail(id,new Order(orderID),new Product(productID), quantity,discount));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            odd.closeAll();
        }
        return list;
    }

    @Override
    public Page<Order_Detail> findOrder_DetailByPage(Page<Order_Detail> page) {
        ArrayList<Order_Detail> list = new ArrayList<>();
        Connection con = DBHelper.getConnection();
        try {
            ResultSet set = odd.selectByPage(con, page);
            int totalRecord = odd.getTotalRecord(con);
            while (set.next()) {
                int id = set.getInt("id");
                String orderID = set.getString("orderID");
                int productID = set.getInt("productID");
                int quantity = set.getInt("quantity");
                double discount = set.getDouble("discount");
                list.add(new Order_Detail(id,new Order(orderID),new Product(productID), quantity,discount));
            }

            page.setList(list);
            page.setTotalRecord(totalRecord);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            odd.closeAll();
        }
        return page;
    }

    @Override
    public int removeOrder_DetailByID(Integer id) {
        Connection con = DBHelper.getConnection();
        try {
            odd.deleteOrder_DetailById(con, id);
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            odd.closeAll();
        }
        return 0;
    }

    @Override
    public int updateOrder_DetailByID(Order_Detail ord) {
        Connection con = DBHelper.getConnection();
        try {
            int i = odd.updateOrder_DetailById(con, ord);
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            odd.closeAll();
        }
        return 0;
    }

    @Override
    public Order_Detail findOrder_DetailByID(Integer ids) {
        Connection con = DBHelper.getConnection();
        try {
            ResultSet set = odd.selectOrder_DetailById(con, ids);
            while (set.next()) {
                int id = set.getInt("id");
                String orderID = set.getString("orderID");
                int productID = set.getInt("productID");
                int quantity = set.getInt("quantity");
                double discount = set.getDouble("discount");
                return new Order_Detail(id,new Order(orderID),new Product(productID), quantity,discount);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            odd.closeAll();
        }
        return null;
    }
}
