package com.bus.service;

import com.bus.entity.Order_Detail;
import com.bus.util.Page;

import java.util.List;

public interface Order_DetailService {
    int addOrder_Detail(Order_Detail ord);//增加

    List<Order_Detail> findAllOrder_Detail();//查找所有产品

    Page<Order_Detail> findOrder_DetailByPage(Page<Order_Detail> page);

    //Page<Order_Detail> findOrder_DetailByCondition(String name, Page<Order_Detail> page);

    int  removeOrder_DetailByID(Integer id);//删

    int updateOrder_DetailByID(Order_Detail ord);//改

    Order_Detail findOrder_DetailByID(Integer id);//查
}
