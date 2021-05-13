package com.intouch.ssm.dao;

import com.intouch.ssm.domain.User;

public interface UserMapper {
    //增加
    int insert(User record);
    int insertSelective(User record);
    //删除
    int deleteByPrimaryKey(Integer id);
    //修改
    int updateByPrimaryKey(User record);
    int updateByPrimaryKeySelective(User record);
    //按照id查询
    User selectByPrimaryKey(Integer id);

    //按照邮箱查询
    User selectByEmailField(String email);
}