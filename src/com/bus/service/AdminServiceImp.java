package com.bus.service;

import com.bus.dao.AdminDaoImp;
import com.bus.entity.Admin;
import com.bus.util.DBHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminServiceImp implements AdminService {
    private AdminDaoImp ad;

    public AdminServiceImp() {
        this.ad = new AdminDaoImp();
    }

    @Override
    public int addAdmin(Admin admin) {
        int i = 0;
        Connection con = DBHelper.getConnection();
        try {
            con.setAutoCommit(false);
            i = ad.insertAdmin(con,admin);
            con.commit();
            con.setAutoCommit(true);
            return i;

        } catch (SQLException e) {
            try {
                con.rollback();
                con.setAutoCommit(true);
            } catch (SQLException w) {
                w.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            ad.closeAll();
        }

        return 0;
    }

    /**
     *
     * @param admin
     * @return  1:代表登录
     *          0:代表用户名错误
     *         -1:代表密码错误
     */
    @Override
    public int findAdminByLogin(Admin admin) {
        Connection con = DBHelper.getConnection();
        try {
            ResultSet set = ad.selectByLogin(con, admin);
            if (set.next()){
                String admin_password = set.getString("admin_password");
                if (admin_password.equals(admin.getAdmin_password())){
                    return 1;
                }else{
                    return -1;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ad.closeAll();
        }
        return 0;
    }

    @Override
    public List<Admin> findAllAdmins() {
        Connection con = DBHelper.getConnection();
        ArrayList<Admin> list = new ArrayList<>();
        try {
            ResultSet set = ad.selectAllAdmins(con);
            while(set.next()) {
                int admin_id = set.getInt("admin_id");
                String adminName = set.getString("admin_name");
                String adminPassword = set.getString("admin_password");
                list.add(new Admin(admin_id,adminName,adminPassword));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ad.closeAll();
        }
        return list;
    }

    @Override
    public int deleteAdminByID(int id) {
        System.out.println("已删除"+id+"号用户！");
        return 0;
    }

}
