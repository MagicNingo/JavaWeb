package com.bus.entity;

public class Role {
    private Integer roleId;
    private String roleName;
    private String roleInfo;

    public Role() {
    }

    public Role(Integer roleId, String roleName, String roleInfo) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleInfo = roleInfo;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleInfo() {
        return roleInfo;
    }

    public void setRoleInfo(String roleInfo) {
        this.roleInfo = roleInfo;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", roleInfo='" + roleInfo + '\'' +
                '}';
    }
}
