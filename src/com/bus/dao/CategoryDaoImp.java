package com.bus.dao;

import com.bus.entity.Category;
import com.bus.util.Page;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryDaoImp extends BaseDao implements CategoryDao{
    @Override
    public int insertCategory(Connection con, Category cate) throws SQLException {
        String sql = "insert into Category(category_name,category_desc) values(?,?)";
        //Oracle数据库
        /*String sql = "insert into Category(categoryID,category_name,category_desc) values(category_seq.nextval,?,?)";*/
        Object[] obj = {cate.getCategory_name(),cate.getCategory_desc()};
        return super.insert(con,sql,obj);
    }

    @Override
    public int[] insertMore(Connection con, Category... cate) throws SQLException {
        return new int[0];
    }

    @Override
    public int deleteCategoryById(Connection con, Integer id) throws SQLException {
        String sql = "delete from category where categoryID = ?";
        return super.delete(con,sql,id);
    }

    @Override
    public int updateCategoryById(Connection con, Category cate) throws SQLException {
        String sql = "update category set category_name =?,category_desc =? where categoryID =?";
        Object[] obj = {cate.getCategory_name(),cate.getCategory_desc(),cate.getCategoryID()};
        return super.update(con,sql,obj);
    }

    @Override
    public ResultSet selectAllCategories(Connection con) throws SQLException {
        String sql = "select * from category";
        return super.select(con,sql);
    }

    @Override
    public ResultSet selectByPage(Connection con, Page<Category> page) throws SQLException {
        String sql =  "select * from category\n" +
                "order by categoryID limit ?,? ";
        Object[] obj = {(page.getCurrentPage()-1)*page.getPageSize(),page.getPageSize()};
        //Oracle数据库
       /* String sql = "select cate.* from\n " +
                " (select rownum rn,category.* from category where rownum<=?) cate where cate.rn>?";
        Object[] obj = {page.getCurrentPage()*page.getPageSize(),(page.getCurrentPage()-1)*page.getPageSize()};*/
        return super.select(con,sql,obj);
    }

    @Override
    public int getTotalRecord(Connection con) throws SQLException {
        String sql = "select count(categoryID) from category";//前端页面未带搜索功能的查询总页数的sql语句
        ResultSet set = super.select(con, sql);
        if (set.next()){
            return set.getInt(1);
        }

        return 0;
    }
    //前端页面带搜索功能的查询总页数的sql语句
    @Override
    public int getSearchTotalRecord(Connection con, String name) throws SQLException {
        String sql = "select count(categoryID) from category where category_name like  concat('%',?,'%')";
        ResultSet set = super.select(con, sql ,name);
        if (set.next()){
            return set.getInt(1);
        }
        return 0;
    }

    @Override
    public ResultSet selectCategoryById(Connection con, Integer id) throws SQLException {
        String sql ="select * from category where categoryID = ? ";
        return super.select(con,sql,id);
    }

    @Override
    public ResultSet selectCategoryByCondition(Connection con, String name,Page<Category> page) throws SQLException {
        String sql = "select * from category where category_name like  concat('%',?,'%') " +
                " order by categoryID limit ?,? ";
        Object[] obj = {name,(page.getCurrentPage()-1)*page.getPageSize(),page.getPageSize()};
        return super.select(con,sql,obj);
    }

    @Override
    public ResultSet selectCategoryID(Connection con,Integer categoryID) throws SQLException {
        String sql = "select p.categoryID from category cate,product p where " +
                " cate.categoryID=p.categoryID and cate.categoryID=? group by p.categoryID ";
        return super.select(con,sql,categoryID);
    }

    @Override
    public ResultSet selectAllCategoryName(Connection con, String category_name) throws SQLException {
        String sql = "select category_name from category where category_name=?";
        return super.select(con,sql,category_name);
    }
}
