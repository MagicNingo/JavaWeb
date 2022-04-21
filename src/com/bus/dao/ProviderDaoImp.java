package com.bus.dao;

import com.bus.entity.Provider;
import com.bus.util.Page;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProviderDaoImp extends BaseDao implements ProviderDao {
    @Override
    public int insertProvider(Connection con, Provider pro) throws SQLException {
        String sql = "insert into Provider(providerID,provider_name," +
                "provider_add,provider_tel,account,email) values(?,?,?,?,?,?)";
        Object[] obj = {pro.getProviderID(), pro.getProvider_name(), pro.getProvider_add(),
                pro.getProvider_tel(), pro.getAccount(), pro.getEmail()};
        //Oracle数据库
        /*String sql = "insert into Provider(providerID,provider_name," +
                "provider_add,provider_tel,account,email) values(provider_seq.nextval,?,?,?,?,?)";
        Object[] obj={pro.getProvider_name(),pro.getProvider_add(),
                pro.getProvider_tel(),pro.getAccount(),pro.getEmail()};*/
        return super.insert(con, sql, obj);
    }

    @Override
    public int[] insertMore(Connection con, Provider... pro) throws SQLException {
        return new int[0];
    }

    @Override
    public int deleteProviderByID(Connection con, Integer id) throws SQLException {
        String sql = "delete from provider where providerID = ?";
        return super.delete(con, sql, id);
    }

    @Override
    public int updateProviderByID(Connection con, Provider pro) throws SQLException {
        String sql = " update provider set provider_name=?, provider_add=? " +
                ",provider_tel=? ,account=?, email=? where providerID =? ";
        Object[] obj = {pro.getProvider_name(), pro.getProvider_add(),
                pro.getProvider_tel(), pro.getAccount(), pro.getEmail(), pro.getProviderID()};
        return super.update(con, sql, obj);
    }

    @Override
    public ResultSet selectAllProviders(Connection con) throws SQLException {
        String sql = "select * from provider";
        return super.select(con, sql);
    }

    @Override
    public ResultSet selectProviderById(Connection con, Integer id) throws SQLException {
        String sql = "select * from provider where providerID =? ";
        return super.select(con, sql, id);
    }

    @Override
    public ResultSet selectProviderByCondition(Connection con, String name, Page<Provider> page) throws SQLException {
        String sql = "select * from provider where provider_name like  concat('%',?,'%') " +
                " order by providerID limit ?,? ";
        Object[] obj = {name, (page.getCurrentPage() - 1) * page.getPageSize(), page.getPageSize()};
        return super.select(con, sql, obj);
    }

    @Override
    public ResultSet selectAllProviderName(Connection con, String provider_name) throws SQLException {
        String sql = "select provider_name from provider where provider_name=?";
        return super.select(con, sql, provider_name);
    }

    @Override
    public ResultSet selectProviderID(Connection con, Integer providerID) throws SQLException {
        String sql = "select p.providerID from product p,provider pro where pro.providerID=p.providerID \n" +
                "and  pro.providerID=? group by p.providerID";
        return super.select(con, sql, providerID);
    }

    @Override
    public ResultSet selectByPage(Connection con, Page<Provider> page) throws SQLException {
        String sql = "select * from provider\n" +
                "order by providerID limit ?,? ";
        Object[] obj = {(page.getCurrentPage() - 1) * page.getPageSize(), page.getPageSize()};
        /*String sql = "select pd.* from\n" +
                "(select rownum rn,provider.* from provider where rownum<=?) pd where pd.rn>?";
        Object[] obj = {page.getCurrentPage()*page.getPageSize(),(page.getCurrentPage()-1)*page.getPageSize()};*/
        return super.select(con, sql, obj);
    }

    @Override
    public int getTotalRecord(Connection con) throws SQLException {
        String sql = "select count(providerID) from provider";
        ResultSet set = super.select(con, sql);
        if (set.next()) {
            return set.getInt(1);
        }
        return 0;
    }

    @Override
    public int getSearchTotalRecord(Connection con, String name) throws SQLException {
        String sql = "select count(providerID) from provider where provider_name like concat('%',?,'%')";
        ResultSet set = super.select(con, sql, name);
        if (set.next()) {
            return set.getInt(1);
        }
        return 0;
    }
}
