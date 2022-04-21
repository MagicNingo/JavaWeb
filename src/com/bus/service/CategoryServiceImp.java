package com.bus.service;

import com.bus.dao.CategoryDaoImp;
import com.bus.entity.Category;
import com.bus.util.DBHelper;
import com.bus.util.Page;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryServiceImp implements CategoryService {
    private CategoryDaoImp cd;

    public CategoryServiceImp() {
        cd = new CategoryDaoImp();

    }

    @Override
    public int addCategory(Category cate) {
        CategoryServiceImp csi = new CategoryServiceImp();
        Connection con = DBHelper.getConnection();
        try {
            con.setAutoCommit(false);
            int all = csi.findAllCategoryName(cate.getCategory_name());
            if (all == 0) {
                cd.insertCategory(con, cate);
                con.commit();
                con.setAutoCommit(true);
                return 1;//可添加
            } else if (all == 1){
                return -1;//已有该目录
            }
        } catch (SQLException e) {
            try {
                con.rollback();
                con.setAutoCommit(true);
            } catch (SQLException w) {
                w.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            cd.closeAll();
        }
        return 0;//添加失败
    }

    @Override
    public int deleteCategoryByID(Integer id) {
        Connection con = DBHelper.getConnection();
        CategoryServiceImp cs = new CategoryServiceImp();
        try {
            int sci = cs.selectCategoryID(id);
            if (sci == 0) {
                cd.deleteCategoryById(con, id);
                return 1;
            } else {
                return -1;//不能删除该目录
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cd.closeAll();
        }
        return 0;
    }

    @Override
    public int updateCategoryByID(Category cate) {
        Connection con = DBHelper.getConnection();
        try {
            int i = cd.updateCategoryById(con, cate);
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cd.closeAll();
        }
        return 0;
    }

    @Override
    public Page<Category> findCategoryByPage(Page<Category> page) {
        ArrayList<Category> list = new ArrayList<>();
        Connection con = DBHelper.getConnection();
        try {
            ResultSet set = cd.selectByPage(con, page);
            int totalRecord = cd.getTotalRecord(con);
            while (set.next()) {
                int categoryID = set.getInt("categoryID");
                String category_name = set.getString("category_name");
                String category_desc = set.getString("category_desc");
                list.add(new Category(categoryID,category_name,category_desc));
            }
            page.setList(list);
            page.setTotalRecord(totalRecord);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            cd.closeAll();
        }
        return page;
    }

    @Override
    public List<Category> findAllCategory() {
        ArrayList<Category> list = new ArrayList<>();
        Connection con = DBHelper.getConnection();
        try {
            ResultSet set = cd.selectAllCategories(con);
            while (set.next()) {
                int categoryID = set.getInt("categoryID");
                String category_name = set.getString("category_name");
                String category_desc = set.getString("category_desc");

                list.add(new Category(categoryID, category_name, category_desc));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public Category findCategoryById(Integer id) {
        Connection con = DBHelper.getConnection();
        try {
            ResultSet set = cd.selectCategoryById(con, id);
            while (set.next()) {
                int categoryID = set.getInt("categoryID");
                String category_name = set.getString("category_name");
                String category_desc = set.getString("category_desc");

                return new Category(categoryID, category_name, category_desc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int selectCategoryID(Integer categoryID) {
        int i = 0;
        Connection con = DBHelper.getConnection();
        try {
            ResultSet set = cd.selectCategoryID(con, categoryID);
            if (set.next()) {
                return 1;//不可删除
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cd.closeAll();
        }
        return 0;//可以删除
    }

    @Override
    public int findAllCategoryName(String category_name) {
        Connection con = DBHelper.getConnection();
        try {
            ResultSet set = cd.selectAllCategoryName(con, category_name);
            if (set.next()){
                return 1;// 1:代表存在该目录
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cd.closeAll();
        }
        return 0; //0:代表不存在该目录
    }

    @Override
    public Page<Category> findCategoryByCondition(String name,Page<Category> page) {
        ArrayList<Category> list = new ArrayList<>();
        Connection con = DBHelper.getConnection();
        try {
            ResultSet set = cd.selectCategoryByCondition(con, name, page);
            int totalRecord = cd.getSearchTotalRecord(con,name);
            while (set.next()) {
                int categoryID = set.getInt("categoryID");
                String category_name = set.getString("category_name");
                String category_desc = set.getString("category_desc");
                list.add(new Category(categoryID,category_name,category_desc));
            }
            page.setList(list);
            page.setTotalRecord(totalRecord);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            cd.closeAll();
        }
        return page;
    }
}
