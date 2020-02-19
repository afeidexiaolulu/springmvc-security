package com.mytest.security.controller;

import com.mytest.security.pojo.AuthenticationRequest;
import com.mytest.security.pojo.UserDto;
import com.mytest.security.service.AuthenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.xml.ws.Service;

/**
 * @author
 * @version 1.00
 * @time 2020/2/19 0019  下午 5:25
 */
@RestController
public class LoginController {

    @Autowired
    private AuthenService authenticationService;
    /**
     * 用户登录
     * @param authenticationRequest 登录请求
     * @return
     */
//    @PostMapping(value = "/login",produces = {"text/plain;charset=UTF-8"})
//    public String login(AuthenticationRequest authenticationRequest, HttpSession session){
//
//        UserDto userDetails = authenticationService.authentication(authenticationRequest);
//        //登录后将用户信息放入session中
//        session.setAttribute(UserDto.SESSION_USER_KEY, userDetails);
//        return userDetails.getFullname() + " 登录成功";
//    }


    /**
     * 登出方法  将session销毁
     *
     */
    @GetMapping(value = "/logout", produces = {"text/plain;charset=UTF-8"})
    public String loginOut(HttpSession session){
        //销毁session
        session.invalidate();
        return "退出系统";
    }


    /**
     * 修改LoginController，增加测试资源1，它从当前会话session中获取当前登录用户，并返回提示信息给前台
     */
    @GetMapping(value = "/r/r1", produces = {"text/plain;charset=UTF-8"})
    public String r1(HttpSession session){
        String name = "";
        Object attribute = session.getAttribute(UserDto.SESSION_USER_KEY);
        if(attribute == null) {
            name = "匿名";
        }else {
            name = ((UserDto) attribute).getFullname();
        }

        return name + "访问资源r1";
    }

    /**
     * 测试资源2
     * @param session
     * @return
     */
    @GetMapping(value = "/r/r2",produces = {"text/plain;charset=UTF-8"})
    public String r2(HttpSession session){
        String fullname = null;
        Object userObj = session.getAttribute(UserDto.SESSION_USER_KEY);
        if(userObj != null){
            fullname = ((UserDto)userObj).getFullname();
        }else{
            fullname = "匿名";
        }
        return fullname + " 访问资源2";
    }

    @RequestMapping(value = "/login-success",produces = {"text/plain;charset=UTF-8"})
    public String loginSuccess(){
        return " 登录成功";
    }
}