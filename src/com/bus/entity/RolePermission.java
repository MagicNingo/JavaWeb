package com.bus.entity;

public class RolePermission {
    private Integer id;
    private Role role;
    private Permission per;

    public RolePermission() {
    }

    public RolePermission(Integer id, Role role, Permission per) {
        this.id = id;
        this.role = role;
        this.per = per;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Permission getPer() {
        return per;
    }

    public void setPer(Permission per) {
        this.per = per;
    }

    @Override
    public String toString() {
        return "RolePermission{" +
                "id=" + id +
                ", roleId=" + role.getRoleId() +
                ", permissionId=" + per.getPermissionId() +
                '}';
    }
}
