package com.bus.entity;

import java.util.Date;

public class Product {
        private Integer productID;
        private String  product_name;
        private Double  income_price;
        private Provider pro;
        private Integer quantity;
        private Double  sales_price;
        private Category cate;
        private Date    income_time;

    public Product() {
    }

    public Product(String product_name, Double income_price, Provider pro, Integer quantity,
                   Double sales_price, Category cate, Date income_time) {
        this.product_name = product_name;
        this.income_price = income_price;
        this.pro = pro;
        this.quantity = quantity;
        this.sales_price = sales_price;
        this.cate = cate;
        this.income_time = income_time;
    }

    public Product(Integer productID) {
        this.productID = productID;
    }

    public Product(String product_name, Double income_price, Provider pro,
                   Integer quantity, Double sales_price, Category cate) {
        this.product_name = product_name;
        this.income_price = income_price;
        this.pro = pro;
        this.quantity = quantity;
        this.sales_price = sales_price;
        this.cate = cate;

    }


    public Product(Integer productID, String product_name, Double income_price,
                   Provider pro, Integer quantity, Double sales_price,
                   Category cate, Date income_time) {
        this.productID = productID;
        this.product_name = product_name;
        this.income_price = income_price;
        this.pro = pro;
        this.quantity = quantity;
        this.sales_price = sales_price;
        this.cate = cate;
        this.income_time = income_time;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Double getIncome_price() {
        return income_price;
    }

    public void setIncome_price(Double income_price) {
        this.income_price = income_price;
    }

    public Provider getPro() {
        return pro;
    }

    public void setPro(Provider pro) {
        this.pro = pro;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getSales_price() {
        return sales_price;
    }

    public void setSales_price(Double sales_price) {
        this.sales_price = sales_price;
    }

    public Category getCate() {
        return cate;
    }

    public void setCate(Category cate) {
        this.cate = cate;
    }

    public Date getIncome_time() {
        return income_time;
    }

    public void setIncome_time(Date income_time) {
        this.income_time = income_time;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", product_name='" + product_name + '\'' +
                ", income_price=" + income_price +
                ", providerID=" + pro.getProviderID() +
                ", provider_Name=" + pro.getProvider_name() +
                ", quantity=" + quantity +
                ", sales_price=" + sales_price +
                ", categoryID=" + cate.getCategoryID() +
                ", category_name=" + cate.getCategory_name() +
                ", income_time=" + income_time +
                '}';
    }
}
