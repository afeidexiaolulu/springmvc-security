package com.mytest.security.service.impl;

import com.mytest.security.pojo.AuthenticationRequest;
import com.mytest.security.pojo.UserDto;
import com.mytest.security.service.AuthenService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 认证实现
 *
 * @author
 * @version 1.00
 * @time 2020/2/19 0019  下午 5:20
 */

@Service
public class AuthenServiceImpl implements AuthenService {

    @Override
    public UserDto authentication(AuthenticationRequest authenticationRequest) {
        if (authenticationRequest == null
                || StringUtils.isEmpty(authenticationRequest.getUsername())
                || StringUtils.isEmpty(authenticationRequest.getPassword())) {
            throw new RuntimeException("账号或密码为空");
        }
        UserDto userDto = getUserDto(authenticationRequest.getUsername());
        if (userDto == null) {
            throw new RuntimeException("查询不到该用户");
        }
        if (!authenticationRequest.getPassword().equals(userDto.getPassword())) {
            throw new RuntimeException("账号或密码错误");
        }
        return userDto;
    }

    //模拟用户查询
    public UserDto getUserDto(String username) {
        return userMap.get(username);
    }

    //用户信息
    private Map<String, UserDto> userMap = new HashMap<>();

    {
        HashSet<String> authorities1 = new HashSet<>();
        authorities1.add("p1");
        HashSet<String> authorities2 = new HashSet<>();
        authorities2.add("p2");

        userMap.put("zhangsan", new UserDto("1010", "zhangsan", "123", "张三", "133443", authorities1));
        userMap.put("lisi", new UserDto("1011", "lisi", "456", "李四", "144553", authorities2));
    }

}
