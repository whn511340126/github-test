package com.intouch.ssm.service;

import com.intouch.ssm.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class UserServiceImplTest {
    @Autowired
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        //输出UserService对象的类名
        System.out.println(userService.getClass().getName() );
    }

    @Test
    public void login() {
        String email="wangda1@126.com";
//        try {
//            sqlSession.begin();
            User user = userService.login(email);
//            sqlSession.commit();
//        }catch (Exception ex){
//            ex.printStackTrace();
//            sqlSession.rollback();
//        }finally{
//            sqlSession.close();
//        }


        System.out.println(user.getId()+" "+user.getEmail()+" "+user.getNickname()+" "+user.getPassword()+" "+
                user.getUserIntegral()+" "+user.getIsEmailVerify()+" "+user.getEmailVerifyCode()+" "+
                user.getLastLoginTime()+" "+user.getLastLoginIp());
    }

    @Test
    public void modifyUser() {
        String email="wangda1@126.com";
        User user = userService.login(email);
       //记录最新登录时间
        user.setLastLoginTime(System.currentTimeMillis());
        //记录最新IP地址
        user.setLastLoginIp("192.168.200.101");
        userService.modifyUser(user);
    }
}