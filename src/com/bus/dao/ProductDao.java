package com.bus.dao;

import com.bus.entity.Product;
import com.bus.util.Page;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface ProductDao {
    int insertProduct(Connection con, Product product)throws SQLException;

    //一次性增加多条记录
    int[] insertMore(Connection con, Product... product)throws SQLException;

    //删
    int deleteProductById(Connection con,Integer id)throws SQLException;

    //改
    int updateProductById(Connection con,Product product)throws SQLException;

    //查
    ResultSet selectAllProducts(Connection con)throws SQLException;
    
    //查
    ResultSet selectProductById(Connection con,Integer id)throws SQLException;

    //查
    ResultSet selectByPage(Connection con, Page<Product> page)throws SQLException;

    int getTotalRecord(Connection con)throws SQLException;

    int getSearchTotalRecord(Connection con , String name)throws SQLException;
    //查
    ResultSet selectProductByCondition(Connection con, String name, Page<Product> page)throws SQLException;


    ResultSet selectAllProductName(Connection con,String product_name)throws SQLException;



}
