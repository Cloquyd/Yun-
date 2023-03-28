package com.fc.yuntieba.utils;

import cn.hutool.core.util.StrUtil;
import com.fc.yuntieba.dto.UserDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {

    private StringRedisTemplate stringRedisTemplate;

    public LoginInterceptor(StringRedisTemplate stringRedisTemplate){
        this.stringRedisTemplate = stringRedisTemplate;
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("Authorization");

        if (StrUtil.isBlank(token)) {
            System.out.println("被拦截了1");
            System.out.println(token);
            return false;
        }
        String id = stringRedisTemplate.opsForValue().get("login:token:" + token);

        if(StrUtil.isBlank(id)){
            System.out.println("被拦截了2");
            return false;
        }
        return true;
    }
}
