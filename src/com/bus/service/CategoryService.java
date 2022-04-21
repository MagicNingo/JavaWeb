package com.bus.service;

import com.bus.entity.Category;

import com.bus.util.Page;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

public interface CategoryService {

    int addCategory(Category cate);

    int deleteCategoryByID(Integer id);

    int updateCategoryByID(Category cate);//改

    Page<Category> findCategoryByPage(Page<Category> page);

    List<Category> findAllCategory();//查找所有目录

    Category findCategoryById(Integer id);//查找目录

    int selectCategoryID(Integer categoryID);

    int findAllCategoryName(String category_name);

    Page<Category> findCategoryByCondition(String name,Page<Category> page);
}
