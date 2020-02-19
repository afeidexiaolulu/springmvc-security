package com.mytest.security.service;

import com.mytest.security.pojo.AuthenticationRequest;
import com.mytest.security.pojo.UserDto;

/**
 * @author
 * @version 1.00
 * @time 2020/2/19 0019  下午 5:19
 */
public interface AuthenService {
    /**
     * 用户认证
     * @param authenticationRequest 用户认证请求
     * @return 认证成功的用户信息
     */
    UserDto authentication(AuthenticationRequest authenticationRequest);
}
