package com.bus.service;

import com.bus.dao.MyFileDao;
import com.bus.dao.MyFileDaoImp;
import com.bus.entity.*;
import com.bus.util.DBHelper;
import com.bus.util.Page;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MyFileServiceImp implements MyFileService{

    private MyFileDaoImp mdi;

    public MyFileServiceImp(){
        mdi = new MyFileDaoImp();
    }

    @Override
    public int addFile(MyFile file) {
        int i;
        Connection con = DBHelper.getConnection();
        try {
            con.setAutoCommit(false);
            i = mdi.insertFile(con,file);
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
            mdi.closeAll();
        }
        return 0;
    }

    @Override
    public int removeFileById(Integer id) {
        Connection con = DBHelper.getConnection();
        try {
            mdi.deleteFileById(con, id);
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mdi.closeAll();
        }
        return 0;
    }

    @Override
    public int updateFileById(MyFile file) {
        Connection con = DBHelper.getConnection();
        try {
            int i =mdi.updateFileById(con, file);
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mdi.closeAll();
        }
        return 0;
    }

    @Override
    public List<MyFile> findAllFiles() {
        Connection con = DBHelper.getConnection();
        ArrayList<MyFile> list = new ArrayList<>();
        try {
            ResultSet set = mdi.selectAllFiles(con);
            while(set.next()) {
                int id= set.getInt("id");
                String fileName= set.getString("fileName");
                String autoName= set.getString("autoName");
                String fileDesc= set.getString("fileDesc");
                String userName= set.getString("userName");

                list.add(new MyFile(id,fileName,autoName,fileDesc,userName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mdi.closeAll();
        }
        return list;
    }

    @Override
    public MyFile findFileById(Integer fileId) {
        Connection con = DBHelper.getConnection();
        try {
            ResultSet set = mdi.selectFileById(con , fileId);
            while(set.next()) {
                int id= set.getInt("id");
                String fileName= set.getString("fileName");
                String autoName= set.getString("autoName");
                String fileDesc= set.getString("fileDesc");
                String userName= set.getString("userName");

                return new MyFile(id,fileName,autoName,fileDesc,userName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mdi.closeAll();
        }
        return  null;
    }

    @Override
    public List<MyFile> findFileByUserName(String user_name) {
        ArrayList<MyFile> list = new ArrayList<>();
        Connection con = DBHelper.getConnection();
        try {
            ResultSet set = mdi.selectFileByUserName(con,user_name);
            while (set.next()) {
                int id= set.getInt("id");
                String fileName= set.getString("fileName");
                String autoName= set.getString("autoName");
                String fileDesc= set.getString("fileDesc");
                String userName= set.getString("userName");

                list.add(new MyFile(id,fileName,autoName,fileDesc,userName));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mdi.closeAll();
        }
        return list;
    }

    @Override
    public List<MyFile> findFileByFileName(String file_name) {
        ArrayList<MyFile> list = new ArrayList<>();
        Connection con = DBHelper.getConnection();
        try {
            ResultSet set = mdi.selectFileByUserName(con,file_name);
            while (set.next()) {
                int id= set.getInt("id");
                String fileName= set.getString("fileName");
                String autoName= set.getString("autoName");
                String fileDesc= set.getString("fileDesc");
                String userName= set.getString("userName");

                list.add(new MyFile(id,fileName,autoName,fileDesc,userName));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mdi.closeAll();
        }
        return list;
    }

    @Override
    public Page<MyFile> findMyFileByPage(Page<MyFile> page) {
        ArrayList<MyFile> list = new ArrayList<>();
        Connection con = DBHelper.getConnection();
        try {
            ResultSet set = mdi.selectByPage(con, page);
            int totalRecord = mdi.getTotalRecord(con);
            while (set.next()) {
                int id= set.getInt("id");
                String fileName= set.getString("fileName");
                String autoName= set.getString("autoName");
                String fileDesc= set.getString("fileDesc");
                String userName= set.getString("userName");
                list.add(new MyFile(id,fileName,autoName,fileDesc,userName));
            }
            page.setList(list);
            page.setTotalRecord(totalRecord);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return page;
    }

}
