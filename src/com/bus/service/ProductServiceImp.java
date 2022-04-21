package com.bus.service;

import com.bus.dao.ProductDaoImp;
import com.bus.entity.Category;
import com.bus.entity.Product;
import com.bus.entity.Provider;
import com.bus.util.DBHelper;
import com.bus.util.Page;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImp implements ProductService {

    private ProductDaoImp pd;

    public ProductServiceImp() {
        pd = new ProductDaoImp();
    }

    @Override
    public int addProduct(Product p) {
        ProductServiceImp ps = new ProductServiceImp();
        Connection con = DBHelper.getConnection();
        try {
            con.setAutoCommit(false);
            int all = ps.findAllProductName(p.getProduct_name());
            if (all == 0) {
                pd.insertProduct(con, p);
                con.commit();
                con.setAutoCommit(true);
                return 1;//可添加
            } else {
                return -1;//已有该产品
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
            pd.closeAll();
        }
        return 0;//添加失败
    }

    @Override
    public int removeProductByID(Integer id) {

        Connection con = DBHelper.getConnection();
        try {
            pd.deleteProductById(con, id);
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pd.closeAll();
        }
        return 0;
    }

    @Override
    public int updateProductByID(Product p) {
        Connection con = DBHelper.getConnection();
        try {
            int i = pd.updateProductById(con, p);
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pd.closeAll();
        }
        return 0;
    }

    @Override
    public Product findProductByID(Integer id) {
        Connection con = DBHelper.getConnection();
        try {
            ResultSet set = pd.selectProductById(con, id);
            while (set.next()) {
                int productID = set.getInt("productID");
                String product_name = set.getString("product_name");
                double income_price = set.getDouble("income_price");

                int providerID = set.getInt("providerID");
                String provider_name = set.getString("provider_name");

                int quantity = set.getInt("quantity");
                double sales_price = set.getDouble("sales_price");

                int categoryID = set.getInt("categoryID");
                String category_name = set.getString("category_name");

//                Date income_time = set.getDate("income_time");
                Timestamp income_time = set.getTimestamp("income_time");
                return new Product(productID, product_name, income_price,
                        new Provider(providerID, provider_name), quantity, sales_price,
                        new Category(categoryID, category_name), income_time);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pd.closeAll();
        }
        return null;
    }


    @Override
    public List<Product> findAllProduct() {
        ArrayList<Product> list = new ArrayList<>();
        Connection con = DBHelper.getConnection();
        try {
            ResultSet set = pd.selectAllProducts(con);
            while (set.next()) {
                int productID = set.getInt("productID");
                String product_name = set.getString("product_name");
                double income_price = set.getDouble("income_price");

                int providerID = set.getInt("providerID");
                String provider_name = set.getString("provider_name");

                int quantity = set.getInt("quantity");
                double sales_price = set.getDouble("sales_price");

                int categoryID = set.getInt("categoryID");
                String category_name = set.getString("category_name");

//                Date income_time = set.getDate("income_time");
                Timestamp income_time = set.getTimestamp("income_time");
                list.add(new Product(productID, product_name, income_price,
                        new Provider(providerID, provider_name), quantity, sales_price,
                        new Category(categoryID, category_name), income_time));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pd.closeAll();
        }
        return list;
    }

    @Override
    public Page<Product> findProductByPage(Page<Product> page) {
        ArrayList<Product> list = new ArrayList<>();
        Connection con = DBHelper.getConnection();
        try {
            ResultSet set = pd.selectByPage(con, page);
            int totalRecord = pd.getTotalRecord(con);
            while (set.next()) {
                int productID = set.getInt("productID");
                String product_name = set.getString("product_name");
                double income_price = set.getDouble("income_price");

                int providerID = set.getInt("providerID");
                String provider_name = set.getString("provider_name");

                int quantity = set.getInt("quantity");
                double sales_price = set.getDouble("sales_price");

                int categoryID = set.getInt("categoryID");
                String category_name = set.getString("category_name");

                /*Date income_time = set.getDate("income_time");*/
                Timestamp income_time = set.getTimestamp("income_time");

                list.add(new Product(productID, product_name, income_price,
                        new Provider(providerID, provider_name), quantity, sales_price,
                        new Category(categoryID, category_name), income_time));
            }

            page.setList(list);
            page.setTotalRecord(totalRecord);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pd.closeAll();
        }
        return page;
    }

    @Override
    public Page<Product> findProductByCondition(String name, Page<Product> page) {
        ArrayList<Product> list = new ArrayList<>();
        Connection con = DBHelper.getConnection();
        try {
            ResultSet set = pd.selectProductByCondition(con, name, page);
            int totalRecord = pd.getSearchTotalRecord(con, name);
            while (set.next()) {
                int productID = set.getInt("productID");
                String product_name = set.getString("product_name");
                double income_price = set.getDouble("income_price");

                int providerID = set.getInt("providerID");
                String provider_name = set.getString("provider_name");

                int quantity = set.getInt("quantity");
                double sales_price = set.getDouble("sales_price");

                int categoryID = set.getInt("categoryID");
                String category_name = set.getString("category_name");

                /*Date income_time = set.getDate("income_time");*/
                Timestamp income_time = set.getTimestamp("income_time");

                list.add(new Product(productID, product_name, income_price,
                        new Provider(providerID, provider_name), quantity, sales_price,
                        new Category(categoryID, category_name), income_time));
            }

            page.setList(list);
            page.setTotalRecord(totalRecord);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pd.closeAll();
        }
        return page;
    }


    @Override
    public int findAllProductName(String product_name) {
        Connection con = DBHelper.getConnection();
        try {
            ResultSet set = pd.selectAllProductName(con, product_name);
            if (set.next()) {
                return 1;// 1:代表存在该产品
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; //0:代表不存在该产品
    }
}
