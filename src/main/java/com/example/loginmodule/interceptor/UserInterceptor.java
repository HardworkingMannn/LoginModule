package com.example.loginmodule.interceptor;

import com.alibaba.fastjson.JSON;
import com.example.loginmodule.pojo.Result;
import com.example.loginmodule.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Slf4j
@Component
public class UserInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtUtil jwtUtil;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            String token = request.getHeader("token");
            Integer userId = jwtUtil.verifyToken(token);
            if (userId != null && request.getSession().getAttribute("userId") == null) {
                request.getSession().setAttribute("userId", userId);
                log.info("userId初始化完成");
            }
            if (userId == null) {
                log.error("token错误:{}", token);
                writeFailResult(response);
            }
            return userId != null;
        }finally {
            writeFailResult(response);
            return false;
        }
    }
    public void writeFailResult(HttpServletResponse response) throws IOException {
        Result fail = Result.fail("拒绝访问，token错误");
        response.setContentType("application/json");
        response.getWriter().print(fail);
    }
}
