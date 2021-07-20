package com.pjb.config;

import com.pjb.dao.UserDao;
import com.pjb.entity.PermissionInfo;
import com.pjb.entity.UserInfo;
import com.pjb.entity.RoleInfo;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Shiro域
 * @author pan_junbiao
 **/
public class ShiroRealm extends AuthorizingRealm
{
    @Resource
    private UserDao userDao;

    @Override
    /**
     * 权限配置
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
    {
        //创建Shiro授权对象
        SimpleAuthorizationInfo authorization = new SimpleAuthorizationInfo();

        //获取用户信息
        UserInfo staffInfo = (UserInfo) principals.getPrimaryPrincipal();

        //遍历角色与权限
        List<RoleInfo> roleInfoList = staffInfo.getRoleList();
        if (roleInfoList != null && roleInfoList.size() > 0)
        {
            roleInfoList.forEach(role ->
            {
                //添加角色信息
                authorization.addRole(role.getRoleCode());

                //添加权限信息
                List<PermissionInfo> permissionInfoList = role.getPermissionInfoList();
                if (permissionInfoList != null && permissionInfoList.size() > 0)
                {
                    List<String> permissions = permissionInfoList.stream().map(PermissionInfo::getPermissionCode).collect(Collectors.toList());
                    authorization.addStringPermissions(permissions);
                }
            });
        }

        return authorization;
    }

    /**
     * 进行身份认证,判断用户名密码是否匹配正确
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException
    {
        //获取用户的输入的账号
        String userName = (String) token.getPrincipal();

        //System.out.println("身份："+userName);
        //System.out.println("凭证："+token.getCredentials());

        if (userName == null || userName.length() == 0)
        {
            return null;
        }

        //获取用户信息
        UserInfo userInfo = userDao.getStaffByUserName(userName);
        if (userInfo == null)
        {
            throw new UnknownAccountException(); //未知账号
        }

        //判断账号是否被锁定，状态（0：禁用；1：锁定；2：启用）
        if(userInfo.getState() == 0)
        {
            throw new DisabledAccountException(); //帐号禁用
        }

        if (userInfo.getState() == 1)
        {
            throw new LockedAccountException(); //帐号锁定
        }

        //验证
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo, //用户名
                userInfo.getPassword(), //密码
                ByteSource.Util.bytes(userInfo.getSalt()), //盐
                getName() //realm name
        );
        return authenticationInfo;
    }

    /*
        DisabledAccountException（禁用的帐号）、LockedAccountException（锁定的帐号）、
        UnknownAccountException（错误的帐号）、ExcessiveAttemptsException（登录失败次数过多）、
        IncorrectCredentialsException （错误的凭证）、ExpiredCredentialsException（过期的凭证）等
    */
}