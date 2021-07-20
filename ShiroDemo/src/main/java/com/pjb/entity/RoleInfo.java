package com.pjb.entity;

import javax.persistence.*;
import java.util.List;

/**
 * 角色信息实体类
 * @author pan_junbiao
 **/
@Entity
@Table(name="role_info")
public class RoleInfo
{
    //角色ID（主键、自增）
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int roleId;

    //角色编号
    @Column(name = "role_code")
    private String roleCode;

    //角色名称
    @Column(name = "role_name")
    private String roleName;

    //权限实体对象集合
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "role_permission_mapping",joinColumns = {@JoinColumn(name = "role_id")},inverseJoinColumns = {@JoinColumn(name="permission_id")})
    private List<PermissionInfo> permissionInfoList;

    //省略getter与setter方法...

    public int getRoleId()
    {
        return roleId;
    }

    public void setRoleId(int roleId)
    {
        this.roleId = roleId;
    }

    public String getRoleCode()
    {
        return roleCode;
    }

    public void setRoleCode(String roleCode)
    {
        this.roleCode = roleCode;
    }

    public String getRoleName()
    {
        return roleName;
    }

    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

    public List<PermissionInfo> getPermissionInfoList()
    {
        return permissionInfoList;
    }

    public void setPermissionInfoList(List<PermissionInfo> permissionInfoList)
    {
        this.permissionInfoList = permissionInfoList;
    }
}