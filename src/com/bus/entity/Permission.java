package com.bus.entity;

public class Permission {
    private Integer permissionId;
    private String pname;
    private String purl;
    private Integer ismenu;
    private Integer parentId;
    private String pinfo;

    public Permission() {
    }

    public Permission(Integer permissionId, String pname, String purl,
                      Integer ismenu, Integer parentId, String pinfo) {
        this.permissionId = permissionId;
        this.pname = pname;
        this.purl = purl;
        this.ismenu = ismenu;
        this.parentId = parentId;
        this.pinfo = pinfo;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }

    public Integer getIsmenu() {
        return ismenu;
    }

    public void setIsmenu(Integer ismenu) {
        this.ismenu = ismenu;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getPinfo() {
        return pinfo;
    }

    public void setPinfo(String pinfo) {
        this.pinfo = pinfo;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "permissionId=" + permissionId +
                ", pname='" + pname + '\'' +
                ", purl='" + purl + '\'' +
                ", ismenu=" + ismenu +
                ", parentId=" + parentId +
                ", pinfo='" + pinfo + '\'' +
                '}';
    }
}
