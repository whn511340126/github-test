package com.intouch.ssm.service;

import com.intouch.ssm.domain.User;

public interface UserService {
    //需求1:登录
    public User login(String email);

    //需求2:修改用户的信息
    // 记录用户的最新登录时间和IP地址
    public void modifyUser(User user);
}
