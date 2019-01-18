package com.hello.ssm.service.impl;

import com.hello.ssm.dao.UserDao;
import com.hello.ssm.entity.User;
import com.hello.ssm.service.IUserService;
import com.hello.utils.EncryptUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Title: IUserServiceImpl
 * @Auther: Michael
 * @Date: 2018/11/15
 */
@Service
public class UserServiceImpl implements IUserService {
    @Resource
    private UserDao userDao;

    @Override
    public User login(String username, String password) {
        password = EncryptUtil.encryptSHA(password);
        return userDao.validateUser(username, password);
    }
}
