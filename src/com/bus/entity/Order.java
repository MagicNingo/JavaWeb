package com.bus.entity;

import java.util.Date;

public class Order {
    private String orderID;
    private Date order_date;
    private Customer cus;
    private Employee emp;

    public Order() {
    }

    public Order(String orderID) {
        this.orderID = orderID;
    }

    public Order(String orderID, Date order_date, Customer cus, Employee emp) {
        this.orderID = orderID;
        this.order_date = order_date;
        this.cus = cus;
        this.emp = emp;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public Customer getCus() {
        return cus;
    }

    public void setCus(Customer cus) {
        this.cus = cus;
    }

    public Employee getEmp() {
        return emp;
    }

    public void setEmp(Employee emp) {
        this.emp = emp;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID='" + orderID + '\'' +
                ", order_date=" + order_date +
                ", customerID=" + cus.getCustomerID() +
                ", customer_name=" + cus.getCustomer_name() +
                ", empID=" + emp.getEmpID() +
                ", emp_name=" + emp.getEmp_name() +
                '}';
    }
}
