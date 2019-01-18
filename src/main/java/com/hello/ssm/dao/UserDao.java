package com.hello.ssm.dao;

import com.hello.ssm.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @Title: UserDao
 * @Auther: Michael
 * @Date: 2018/11/15
 */
public interface UserDao {

    User validateUser(@Param("username") String username, @Param("password") String password);
}
