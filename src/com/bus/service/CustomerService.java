package com.bus.service;

import com.bus.entity.Customer;
import com.bus.util.Page;

import java.util.List;

public interface CustomerService {
    int addCustomer(Customer customer);

    List<Customer> findAllCustomers();

    Page<Customer> findCustomerByPage(Page<Customer> page);

    Page<Customer> findCustomerByCondition(String name, Page<Customer> page);

    int removeCustomerByID(Integer id);

    int updateProductByID(Customer p);//改

    Customer findCustomerByID(Integer id);//查

    int findAllCustomerName(String name);
}
