package com.pjb.controller;

import com.pjb.entity.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 首页控制器
 * @author pan_junbiao
 **/
@Controller
public class IndexController
{
    /**
     * 首页
     */
    @RequestMapping("/index")
    public String index(HttpServletRequest request)
    {
        //获取当前登录人
        UserInfo userInfo = (UserInfo) SecurityUtils.getSubject().getPrincipal();
        request.setAttribute("userInfo",userInfo);
        return "index.html";
    }
}
