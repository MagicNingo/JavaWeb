package com.bus.service;

import com.bus.dao.ProviderDaoImp;
import com.bus.entity.Provider;
import com.bus.util.DBHelper;
import com.bus.util.Page;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProviderServiceImp implements ProviderService {

    private ProviderDaoImp pd;

    public ProviderServiceImp() {
        pd = new ProviderDaoImp();
    }

    @Override
    public int addProvider(Provider pro) {
        ProviderServiceImp psi = new ProviderServiceImp();
        Connection con = DBHelper.getConnection();
        try {
            con.setAutoCommit(false);
            int i = psi.findAllProviderName(pro.getProvider_name());
            if (i == 0) {
                pd.insertProvider(con, pro);
                con.commit();
                con.setAutoCommit(true);
                return 1;//可添加
            } else if (i == 1) {
                return -1;//已有该供应商
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
    public int removeProviderByID(Integer id) {
        Connection con = DBHelper.getConnection();
        ProviderServiceImp ps = new ProviderServiceImp();
        try {
            int psi = ps.selectProviderID(id);
            if (psi == 0) {
                pd.deleteProviderByID(con, id);
                return 1;//
            } else {
                return -1;//不可删除！
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pd.closeAll();
        }
        return 0;
    }

    @Override
    public int updateProviderById(Provider pro) {
        Connection con = DBHelper.getConnection();
        int i = 0;
        try {
            i = pd.updateProviderByID(con, pro);
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pd.closeAll();
        }
        return i;
    }

    @Override
    public Page<Provider> findProviderByPage(Page<Provider> page) {
        ArrayList<Provider> list = new ArrayList<>();
        Connection con = DBHelper.getConnection();
        try {
            ResultSet set = pd.selectByPage(con, page);
            int totalRecord = pd.getTotalRecord(con);
            while (set.next()) {
                int providerID = set.getInt("providerID");
                String provider_name = set.getString("provider_name");
                String provider_add = set.getString("provider_add");
                String provider_tel = set.getString("provider_tel");
                String account = set.getString("account");
                String email = set.getString("email");
                list.add(new Provider(providerID, provider_name, provider_add, provider_tel, account, email));
            }
            page.setList(list);
            page.setTotalRecord(totalRecord);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return page;
    }

    @Override
    public Page<Provider> findCategoryByCondition(String name, Page<Provider> page) {
        ArrayList<Provider> list = new ArrayList<>();
        Connection con = DBHelper.getConnection();
        try {
            ResultSet set = pd.selectProviderByCondition(con, name, page);
            int totalRecord = pd.getSearchTotalRecord(con, name);
            while (set.next()) {
                int providerID = set.getInt("providerID");
                String provider_name = set.getString("provider_name");
                String provider_add = set.getString("provider_add");
                String provider_tel = set.getString("provider_tel");
                String account = set.getString("account");
                String email = set.getString("email");
                list.add(new Provider(providerID, provider_name, provider_add, provider_tel, account, email));
            }
            page.setList(list);
            page.setTotalRecord(totalRecord);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return page;
    }

    @Override
    public List<Provider> findAllProvider() {
        ArrayList<Provider> list = new ArrayList<>();
        Connection con = DBHelper.getConnection();
        try {
            ResultSet set = pd.selectAllProviders(con);
            while (set.next()) {
                int providerID = set.getInt("providerID");
                String provider_name = set.getString("provider_name");
                String provider_add = set.getString("provider_add");
                String provider_tel = set.getString("provider_tel");
                String account = set.getString("account");
                String email = set.getString("email");
                list.add(new Provider(providerID, provider_name, provider_add, provider_tel, account, email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pd.closeAll();
        }

        return list;
    }

    @Override
    public Provider findProviderById(Integer id) {
        Connection con = DBHelper.getConnection();
        try {
            ResultSet set = pd.selectProviderById(con, id);
            if (set.next()) {
                int providerID = set.getInt("providerID");
                String provider_name = set.getString("provider_name");
                String provider_add = set.getString("provider_add");
                String provider_tel = set.getString("provider_tel");
                String account = set.getString("account");
                String email = set.getString("email");

                return new Provider(providerID, provider_name, provider_add, provider_tel, account, email);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pd.closeAll();
        }
        return null;
    }

    @Override
    public int selectProviderID(Integer providerID) {
        int i = 0;
        Connection con = DBHelper.getConnection();
        try {
            ResultSet set = pd.selectProviderID(con, providerID);
            if (set.next()) {
                return 1;//不可删除
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pd.closeAll();
        }
        return 0;//可以删除
    }

    @Override
    public int findAllProviderName(String provider_name) {
        Connection con = DBHelper.getConnection();
        try {
            ResultSet set = pd.selectAllProviderName(con, provider_name);
            if (set.next()) {
                return 1;// 1:代表存在该供应商
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; //0:代表不存在该供应商
    }
}
