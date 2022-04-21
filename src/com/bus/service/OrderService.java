package com.bus.service;

import com.bus.entity.Order;
import com.bus.util.Page;

import java.util.List;

public interface OrderService {
    int addOrder(Order ord);//增加

    List<Order> findAllOrder();//查找所有订单

    Page<Order> findOrderByPage(Page<Order> page);

    //Page<Order> findOrderByCondition(String name, Page<Order> page);

    int  removeOrderByID(String id);//删

    int updateOrderByID(Order ord);//改

    Order findOrderByID(String id);//查

}
