package com.bus.dao;

import com.bus.entity.Provider;
import com.bus.util.Page;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface ProviderDao {
    //增
    int insertProvider(Connection con, Provider pro) throws SQLException;

    //一次性增加多条记录
    int[] insertMore(Connection con, Provider... pro) throws SQLException;

    //删
    int deleteProviderByID(Connection con, Integer id) throws SQLException;

    //改
    int updateProviderByID(Connection con, Provider pro) throws SQLException;

    //查
    ResultSet selectAllProviders(Connection con) throws SQLException;

    //查
    ResultSet selectProviderById(Connection con, Integer id) throws SQLException;

    //查
    ResultSet selectProviderByCondition(Connection con, String name, Page<Provider> page) throws SQLException;

    ResultSet selectAllProviderName(Connection con, String provider_name) throws SQLException;

    //查
    ResultSet selectProviderID(Connection con, Integer providerID) throws SQLException;

    //查
    ResultSet selectByPage(Connection con, Page<Provider> page) throws SQLException;

    int getTotalRecord(Connection con) throws SQLException;

    int getSearchTotalRecord(Connection con, String name) throws SQLException;
}
