package com.intouch.ssm.service;

import com.intouch.ssm.dao.UserMapper;
import com.intouch.ssm.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *  开发登录的业务组件
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public User login(String email) {
        User user=userMapper.selectByEmailField(email);
        return user;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void modifyUser(User user) {
        userMapper.updateByPrimaryKeySelective(user);
        /*
        User user2=new User();
        user2.setId(4);
        user2.setEmail("wangda1@126.com");
        user2.setUserIntegral(10000);
        user2.setLastLoginTime(System.currentTimeMillis());
        user2.setLastLoginIp("192.168.200.104");
        userMapper.updateByPrimaryKeySelective(user2);
        */
    }
}
