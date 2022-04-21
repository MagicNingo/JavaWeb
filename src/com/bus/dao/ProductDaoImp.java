package com.bus.dao;

import com.bus.entity.Product;
import com.bus.util.Page;
import com.bus.util.TimeUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ProductDaoImp extends BaseDao implements ProductDao {
    @Override
    public int insertProduct(Connection con, Product p) throws SQLException {
        String sql = "insert into Product(product_name,income_price," +
                " providerID,quantity,sales_price,categoryID,income_time) values(?,?,?,?,?,?,?)";
        Object[] obj = {p.getProduct_name(), p.getIncome_price(), p.getPro().getProviderID(),
                p.getQuantity(), p.getSales_price(), p.getCate().getCategoryID(),
                TimeUtil.dateToString(p.getIncome_time(), "yyyy-MM-dd")};

        //Oracle数据库
       /* String sql = "insert into product(productID,product_name,income_price," +
                "providerID,quantity,sales_price,categoryID,income_time) " +
                "values(product_seq.nextval,?,?,?,?,?,?,to_date(?,'yyyy-MM-dd'))";
        Object[] obj = {p.getProduct_name(),p.getIncome_price(),p.getPro().getProviderID(),
                p.getQuantity(),p.getSales_price(),p.getCate().getCategoryID(),
                TimeUtil.dateToString(p.getIncome_time(),"yyyy-MM-dd")};*/
        return super.insert(con, sql, obj);
    }

    @Override
    public int[] insertMore(Connection con, Product... p) throws SQLException {
        String sql = "insert into product(product_name,income_price,providerID,quantity," +
                " sales_price,categoryID,income_time) values(?,?,?,?,?,?,?) ";
        Object[][] obj = new Object[p.length][];
        for (int i = 0; i < obj.length; i++) {
            obj[i]=new Object[]{p[i].getProduct_name(), p[i].getIncome_price(), p[i].getPro().getProviderID(),
                    p[i].getQuantity(), p[i].getSales_price(), p[i].getCate().getCategoryID(),
                    TimeUtil.dateToString(p[i].getIncome_time(), "yyyy-MM-dd")};
        }
        return super.insertMore(con,sql,obj);
    }

    @Override
    public int deleteProductById(Connection con, Integer id) throws SQLException {
        String sql = "delete from product where productID=?";
        return super.delete(con, sql, id);
    }

    @Override
    public int updateProductById(Connection con, Product p) throws SQLException {
        String sql = "update product set product_name = ?,income_price = ?" +
                ",providerID = ?,quantity = ?,sales_price = ?,categoryID = ?,income_time= ? where productID = ?";
        //Oracle的sql语句
        /*String sql = "update product set product_name=?,income_price=?" +
                ",providerID=?,quantity=?,sales_price=?,categoryID=?,income_time=to_date(?,'yyyy-MM-dd') where productID = ?";*/
        Object[] obj = {p.getProduct_name(), p.getIncome_price(),
                p.getPro().getProviderID(), p.getQuantity(), p.getSales_price(),
                p.getCate().getCategoryID(), TimeUtil.dateToString(p.getIncome_time(), "yyyy-MM-dd HH:mm:ss"), p.getProductID()};
        return super.update(con, sql, obj);
    }

    @Override
    public ResultSet selectAllProducts(Connection con) throws SQLException {
        String sql = "select pro.*,cate.category_name,pd.provider_name \n" +
                " from product pro,category cate,provider pd\n " +
                " where pro.categoryID = cate.categoryID and pro.providerID = pd.providerID ";
        return super.select(con, sql);
    }

    @Override
    public ResultSet selectProductById(Connection con, Integer id) throws SQLException {
        String sql = "select pro.*,cate.category_name,pd.provider_name \n" +
                "from product pro,category cate,provider pd \n" +
                "where pro.categoryID = cate.categoryID and pro.providerID = pd.providerID and pro.productID = ?";
        return super.select(con, sql, id);
    }

    @Override
    public ResultSet selectByPage(Connection con, Page<Product> page) throws SQLException {
        String sql = "select pro.*,cate.category_name,pd.provider_name \n" +
                " from product pro,category cate,provider pd\n " +
                " where pro.categoryID = cate.categoryID and pro.providerID = pd.providerID " +
                "order by pro.productID limit ?,? ";
        Object[] obj = {(page.getCurrentPage() - 1) * page.getPageSize(), page.getPageSize()};
        /*String sql = "SELECT pro.*,cate.category_name,pd.provider_name FROM\n " +
                " ( SELECT ROWNUM rn, product.* FROM product WHERE ROWNUM <= ? ) pro ,category cate, provider pd\n " +
                " WHERE pro.rn >? and pro.categoryID = cate.categoryID and pro.providerID = pd.providerID";
        Object[] obj = {page.getCurrentPage()*page.getPageSize(),(page.getCurrentPage()-1)*page.getPageSize()};*/

        return super.select(con, sql, obj);
    }

    @Override
    public ResultSet selectProductByCondition(Connection con, String name, Page<Product> page) throws SQLException {
        String sql = "select  pro.*,cate.category_name,pd.provider_name " +
                " from product pro ,category cate, provider pd " +
                "where pro.categoryID = cate.categoryID and pro.providerID = pd.providerID " +
                " and pro.product_name like  concat('%',?,'%') " +
                " order by productID limit ?,? ";
        Object[] obj = {name, (page.getCurrentPage() - 1) * page.getPageSize(), page.getPageSize()};
        return super.select(con, sql, obj);
    }

    @Override
    public int getTotalRecord(Connection con) throws SQLException {
        String sql = "select count(productID) from product";
        ResultSet set = super.select(con, sql);
        if (set.next()) {
            //直接取第一列的值（count(productID)）
            return set.getInt(1);
        }

        return 0;
    }

    @Override
    public int getSearchTotalRecord(Connection con, String name) throws SQLException {
        String sql = "select count(productID) from product where product_name like  concat('%',?,'%') ";
        ResultSet set = super.select(con, sql, name);
        if (set.next()) {
            //直接取第一列的值（count(productID)）
            return set.getInt(1);
        }

        return 0;
    }

    @Override
    public ResultSet selectAllProductName(Connection con, String product_name) throws SQLException {
        String sql = "select product_name from product where product_name=?";
        return super.select(con, sql, product_name);
    }
}
