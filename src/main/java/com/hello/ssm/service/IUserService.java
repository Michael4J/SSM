package com.hello.ssm.service;

import com.hello.ssm.entity.User;

/**
 * @Title: IUserService
 * @Auther: Michael
 * @Date: 2018/11/15
 */
public interface IUserService {

    User login(String username, String password);


}
