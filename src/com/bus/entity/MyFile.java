package com.bus.entity;

public class MyFile {
    private Integer id;
    private String fileName;
    private String autoName;
    private String fileDesc;
    private String userName;

    public MyFile() {
    }

    public MyFile(Integer id, String fileName, String autoName, String fileDesc, String userName) {
        this.id = id;
        this.fileName = fileName;
        this.autoName = autoName;
        this.fileDesc = fileDesc;
        this.userName = userName;
    }

    public MyFile(String fileName, String autoName, String fileDesc, String userName) {
        this.fileName = fileName;
        this.autoName = autoName;
        this.fileDesc = fileDesc;
        this.userName = userName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getAutoName() {
        return autoName;
    }

    public void setAutoName(String autoName) {
        this.autoName = autoName;
    }

    public String getFileDesc() {
        return fileDesc;
    }

    public void setFileDesc(String fileDesc) {
        this.fileDesc = fileDesc;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "MyFile{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", autoName='" + autoName + '\'' +
                ", fileDesc='" + fileDesc + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
