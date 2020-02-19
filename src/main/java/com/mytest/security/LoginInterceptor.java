package com.mytest.security;

import com.mytest.security.pojo.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author
 * @version 1.00
 * @time 2020/2/19 0019  下午 7:41
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    /**
     * 方法执行前拦截
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        /*
         （4）测试
         未登录情况下，/r/r1与/r/r2均提示 “请先登录”。
         张三登录情况下，由于张三有p1权限，因此可以访问/r/r1，张三没有p2权限，访问/r/r2时提示 “权限不足 “。
         李四登录情况下，由于李四有p2权限，因此可以访问/r/r2，李四没有p1权限，访问/r/r1时提示 “权限不足 “。
         测试结果全部符合预期结果。
         */
        //如果没登录拦截
        HttpSession session = request.getSession();
        Object user = session.getAttribute(UserDto.SESSION_USER_KEY);
        if (user == null) {
            writeContent(response, "请先登录");
            return false;
        }

        String requestURI = request.getRequestURI();

        //p1的不拦截r1
        if (((UserDto) user).getAuthorities().contains("p1") && requestURI.contains("/r1")) {
            return true;
        }

        //p2的不拦截r2
        if (((UserDto) user).getAuthorities().contains("p2") && requestURI.contains("/r2")) {
            return true;
        }
        writeContent(response, "权限不足");
        return false;
    }

    //响应输出
    private void writeContent(HttpServletResponse response, String msg) throws IOException {

        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print(msg);
        writer.close();
        response.resetBuffer();
    }
}
