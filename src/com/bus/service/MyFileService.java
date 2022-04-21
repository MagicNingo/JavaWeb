package com.bus.service;

import com.bus.entity.MyFile;
import com.bus.util.Page;

import java.util.List;

public interface MyFileService {
    //增
    int addFile(MyFile file);

    //删
    int removeFileById(Integer id);

    //改
    int updateFileById(MyFile file);
    //查
    List<MyFile> findAllFiles();

    MyFile findFileById(Integer id);

    List<MyFile> findFileByUserName(String userName);

    List<MyFile> findFileByFileName(String fileName);

    Page<MyFile> findMyFileByPage(Page<MyFile> page);
}
