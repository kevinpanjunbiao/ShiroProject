package com.pjb.entity;

import javax.persistence.*;
import java.util.List;

/**
 * 用户信息实体类
 * @author pan_junbiao
 **/
@Entity
@Table(name = "user_info")
public class UserInfo
{
    //用户ID（主键、自增）
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    //用户姓名
    @Column(name = "user_name")
    private String userName;

    //登录密码
    @Column(name = "PASSWORD")
    private String password;

    //盐
    @Column(name = "salt")
    private String salt;

    //状态（0：禁用；1：锁定；2：启用）
    @Column(name = "state")
    private int state;

    //博客地址
    @Column(name = "blog_url")
    private String blogUrl;

    //博客信息
    @Column(name = "blog_remark")
    private String blogRemark;

    //角色实体对象集合
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role_mapping",joinColumns = {@JoinColumn(name = "user_id")},inverseJoinColumns = {@JoinColumn(name="role_id")})
    private List<RoleInfo> roleList;

    //省略getter与setter方法...

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getSalt()
    {
        return salt;
    }

    public void setSalt(String salt)
    {
        this.salt = salt;
    }

    public int getState()
    {
        return state;
    }

    public void setState(int state)
    {
        this.state = state;
    }

    public String getBlogUrl()
    {
        return blogUrl;
    }

    public void setBlogUrl(String blogUrl)
    {
        this.blogUrl = blogUrl;
    }

    public String getBlogRemark()
    {
        return blogRemark;
    }

    public void setBlogRemark(String blogRemark)
    {
        this.blogRemark = blogRemark;
    }

    public List<RoleInfo> getRoleList()
    {
        return roleList;
    }

    public void setRoleList(List<RoleInfo> roleList)
    {
        this.roleList = roleList;
    }
}