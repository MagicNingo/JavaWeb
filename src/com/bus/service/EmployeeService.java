package com.bus.service;

import com.bus.entity.Employee;
import com.bus.util.Page;

import java.util.List;

public interface EmployeeService {
    int addEmployee(Employee emp);//增加

    List<Employee> findAllEmployee();//查找所有产品

    Page<Employee> findEmployeeByPage(Page<Employee> page);

    Page<Employee> findEmployeeByCondition(String name, Page<Employee> page);

    int  removeEmployeeByID(Integer id);//删

    int updateEmployeeByID(Employee emp);//改

    Employee findEmployeeByID(Integer id);//查

    int findAllEmployeeName(String emp_name);
}
