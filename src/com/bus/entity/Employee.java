package com.bus.entity;

import java.util.Date;

public class Employee {
    private Integer empID;
    private String emp_name;
    private Date hire_date;
    private Double salary;

    public Employee() {
    }

    public Employee(Integer empID) {
        this.empID = empID;
    }

    public Employee(Integer empID, String emp_name, Date hire_date, Double salary) {
        this.empID = empID;
        this.emp_name = emp_name;
        this.hire_date = hire_date;
        this.salary = salary;
    }

    public Employee(String emp_name, Date hire_date, Double salary) {
        this.emp_name = emp_name;
        this.hire_date = hire_date;
        this.salary = salary;
    }

    public Integer getEmpID() {
        return empID;
    }

    public void setEmpID(Integer empID) {
        this.empID = empID;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public Date getHire_date() {
        return hire_date;
    }

    public void setHire_date(Date hire_date) {
        this.hire_date = hire_date;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empID=" + empID +
                ", emp_name='" + emp_name + '\'' +
                ", hire_date=" + hire_date +
                ", salary=" + salary +
                '}';
    }
}
