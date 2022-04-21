package com.bus.dao;

import com.bus.entity.Category;
import com.bus.util.Page;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface CategoryDao {
    //增
    int insertCategory(Connection con, Category cate)throws SQLException;

    //一次性增加多条记录
    int[] insertMore(Connection con, Category... cate)throws SQLException;

    //删
    int deleteCategoryById(Connection con,Integer id)throws SQLException;

    //改
    int updateCategoryById(Connection con,Category cate)throws SQLException;

    //查
    ResultSet selectAllCategories(Connection con)throws SQLException;

    //查
    ResultSet selectByPage(Connection con, Page<Category> page)throws SQLException;

    int getTotalRecord(Connection con)throws SQLException;

    int getSearchTotalRecord(Connection con,String name)throws SQLException;


    //查
    ResultSet selectCategoryById(Connection con,Integer id)throws SQLException;

    //查
    ResultSet selectCategoryByCondition(Connection con, String name,Page<Category> page)throws SQLException;

    ResultSet selectCategoryID(Connection con,Integer categoryID)throws SQLException;

    ResultSet selectAllCategoryName(Connection con,String category_name)throws SQLException;
}
