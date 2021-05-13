package com.intouch.ssm.controller;

import com.intouch.ssm.dao.UserMapper;
import com.intouch.ssm.domain.User;
import com.intouch.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    //产生一个输入页面
    @GetMapping("/form")
    public String showForm(Model model){
        model.addAttribute("user",new User());
        return "LoginForm";
    }

    //接收表单数据,实施登录在表现层的处理
    @PostMapping("/login")
    public String handleLogin(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,
                              Model model, HttpServletRequest request){
        //检验是否有效接收数据
        if(bindingResult.hasErrors()){
            return "LoginForm";
        }

        //什么是有效的登录?数据库里的有这个用户.如何才能知道数据库里有?
        User validUser=userService.login(user.getEmail());
        //没有查到或者查到了但是密码不同
        if((validUser==null)||(!validUser.getPassword().equals(user.getPassword()))){
            String errorMessage="用户邮箱或者密码错误,请重新填写!";
            model.addAttribute("errorMessage",errorMessage);
            return "LoginForm";
        }

        //在表现层处理登录
        //(1)记录用户的最新登录时间
        validUser.setLastLoginTime(System.currentTimeMillis());
        //(2)记录用户的最新IP地址
        String remoteIP=request.getRemoteAddr();
        validUser.setLastLoginIp(remoteIP);
        //(3)如何将最新信息存入到数据库中?通过业务组件
        userService.modifyUser(validUser);

        //(4)存入session域中
        HttpSession session=request.getSession();
        session.setAttribute("validUser",validUser);
        return "UserInfo";
    }

    //处理登出
    @GetMapping("/logout")
    public String handleLogout(HttpServletRequest request){
        //(1)销毁session域中的登录用户
        HttpSession session=request.getSession();
        session.removeAttribute("validUser");
        //(2)无效掉当前session对象
        session.invalidate();
        //(3)重定向登录页面
        return "redirect:/user/form";
    }
}
