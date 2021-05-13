package com.intouch.ssm.dao;

import com.intouch.ssm.domain.User;
//import com.intouch.ssm.util.MyBatisSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserMapperTest {
    private SqlSession sqlSession;
    private UserMapper userMapper;

    @Before
    public void setUp(){

//        sqlSession= MyBatisSqlSessionFactory.getSqlSession();
        userMapper=sqlSession.getMapper(UserMapper.class);

    }

    @Test
    public void selectByEmailField() {
        String email="wangda1@126.com";
        User user=userMapper.selectByEmailField(email);
        System.out.println(user.getId()+" "+user.getEmail()+" "+user.getNickname()+" "+user.getPassword()+" "+
                user.getUserIntegral()+" "+user.getIsEmailVerify()+" "+user.getEmailVerifyCode()+" "+
                user.getLastLoginTime()+" "+user.getLastLoginIp());
        sqlSession.commit();
        sqlSession.close();
    }
}