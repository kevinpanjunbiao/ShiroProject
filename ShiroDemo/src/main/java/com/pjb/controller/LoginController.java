package com.pjb.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录控制器
 * @author pan_junbiao
 **/
@Controller
public class LoginController
{
    @Autowired
    public HttpServletRequest request;

    /**
     * 登录页面
     */
    @RequestMapping("/toLoginView")
    public String toLoginView()
    {
        return "login.html";
    }

    /**
     * 登录操作
     */
    @RequestMapping("/login")
    public String login(String userName, String password)
    {
        //参数验证
        if (userName == null || userName.length() == 0 || password == null || password.length() == 0)
        {
            return "login.html";
        }

        //账号密码令牌
        AuthenticationToken token = new UsernamePasswordToken(userName, password);

        //获得当前用户到登录对象，现在状态为未认证
        Subject subject = SecurityUtils.getSubject();

        try
        {
            //将令牌传到shiro提供的login方法验证，需要自定义realm
            subject.login(token);

            //没有异常表示验证成功,进入首页
            return "redirect:/index";
        }
        catch (IncorrectCredentialsException ice)
        {
            request.setAttribute("message", "用户名或密码不正确！");
        }
        catch (UnknownAccountException uae)
        {
            request.setAttribute("message", "未知账户！");
        }
        catch (LockedAccountException lae)
        {
            request.setAttribute("message", "账户被锁定！");
        }
        catch (DisabledAccountException dae)
        {
            request.setAttribute("message", "账户被禁用！");
        }
        catch (ExcessiveAttemptsException eae)
        {
            request.setAttribute("message", "用户名或密码错误次数太多！");
        }
        catch (AuthenticationException ae)
        {
            request.setAttribute("message", "验证未通过！");
        }
        catch (Exception e)
        {
            request.setAttribute("message", "验证未通过！");
        }

        return "login.html";
    }

    /**
     * 登出操作
     */
    @RequestMapping("/logout")
    public String logout()
    {
        //登出清除缓存
        Subject subject = SecurityUtils.getSubject();
        subject.logout();

        return "redirect:/toLoginView";
    }
}
