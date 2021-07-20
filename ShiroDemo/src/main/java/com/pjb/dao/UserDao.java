package com.pjb.dao;

import com.pjb.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * 用户数据访问接口
 * @author pan_junbiao
 **/
public interface UserDao extends JpaRepository<UserInfo, Integer>
{
    /**
     * 根据用户姓名，获取用户信息
     */
    @Query("SELECT u FROM UserInfo u WHERE u.userName = :userName")
    public UserInfo getStaffByUserName(@Param("userName") String userName);
}
