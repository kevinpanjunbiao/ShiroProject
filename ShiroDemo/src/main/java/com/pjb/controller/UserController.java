package com.pjb.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户信息控制器
 * 所有方法仅测试授权功能
 * @author pan_junbiao
 **/
@Controller
@RequestMapping("/user")
public class UserController
{
    @Autowired
    public HttpServletRequest request;

    /**
     * 查询用户
     */
    @RequiresPermissions("user:list")
    @RequestMapping("/searchUser")
    public String searchUser()
    {
        request.setAttribute("message","查询用户");
        return "user.html";
    }

    /**
     * 新增用户
     */
    @RequiresPermissions("user:add")
    @RequestMapping("/addUser")
    public String addUser()
    {
        request.setAttribute("message","新增用户");
        return "user.html";
    }

    /**
     * 修改用户
     */
    @RequiresPermissions("user:edit")
    @RequestMapping("/editUser")
    public String editUser()
    {
        request.setAttribute("message","修改用户");
        return "user.html";
    }

    /**
     * 删除用户
     */
    @RequiresPermissions("user:delete")
    @RequestMapping("/deleteUser")
    public String deleteUser()
    {
        request.setAttribute("message","删除用户");
        return "user.html";
    }

    /**
     * 用户统计（无权限）
     */
    @RequiresPermissions("user:summarize")
    @RequestMapping("/summarizeUser")
    public String summarizeUser()
    {
        request.setAttribute("message","用户统计");
        return "user.html";
    }
}
