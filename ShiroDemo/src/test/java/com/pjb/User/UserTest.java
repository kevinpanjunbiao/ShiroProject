package com.pjb.User;

import com.pjb.dao.UserDao;
import com.pjb.entity.UserInfo;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 用户信息测试类
 * @author pan_junbiao
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserTest
{
    @Autowired
    private UserDao userDao;

    /**
     * 新增用户
     * 账号密码的加密、加盐
     */
    @Test
    public void addUser()
    {
        String originalPassword = "123456"; //原始密码
        String hashAlgorithmName = "MD5"; //加密方式
        int hashIterations = 2; //加密的次数

        //盐
        String salt = new SecureRandomNumberGenerator().nextBytes().toHex();

        //加密
        SimpleHash simpleHash = new SimpleHash(hashAlgorithmName, originalPassword, salt, hashIterations);
        String encryptionPassword = simpleHash.toString();

        //创建用户信息
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("pan_junbiao的博客_02");
        userInfo.setPassword(encryptionPassword);
        userInfo.setSalt(salt);
        userInfo.setBlogUrl("https://blog.csdn.net/pan_junbiao");
        userInfo.setBlogRemark("您好，欢迎访问 pan_junbiao的博客");
        userInfo.setState(2);

        //执行新增
        userDao.save(userInfo);

        //打印结果
        System.out.println("用户ID：" + userInfo.getUserId());
        System.out.println("用户姓名：" + userInfo.getUserName());
        System.out.println("原始密码：" + originalPassword);
        System.out.println("加密密码：" + userInfo.getPassword());
        System.out.println("盐：" + userInfo.getSalt());
        System.out.println("博客地址：" + userInfo.getBlogUrl());
        System.out.println("博客信息：" + userInfo.getBlogRemark());
    }
}
