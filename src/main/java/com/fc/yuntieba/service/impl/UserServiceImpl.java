package com.fc.yuntieba.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.fc.yuntieba.dto.LoginFormDTO;
import com.fc.yuntieba.dto.Result;
import com.fc.yuntieba.dto.UserDTO;
import com.fc.yuntieba.entity.User;
import com.fc.yuntieba.mapper.UserMapper;
import com.fc.yuntieba.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author lqy
 * @since 2023-03-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result login(LoginFormDTO loginFormDTO) {
        User user = query().eq("username", loginFormDTO.getUsername()).eq("password", loginFormDTO.getPassword()).one();

        if(user == null){
            return Result.fail("账号或密码错误！");
        }

//        Long endTime = (long) (14 * 24 * 60 * 60);

        String token = RandomUtil.randomNumbers(12);

        String tokenKey = "login:token:" + token;

        stringRedisTemplate.opsForValue().set(tokenKey,StrUtil.toString(user.getId()),(14 * 24 * 60 * 60), TimeUnit.SECONDS);

        UserDTO userDTO = BeanUtil.copyProperties(user, UserDTO.class);
        userDTO.setToken(token);

        return Result.ok(userDTO);
    }

    @Override
    public Result register(User user) {
        User one = query().eq("username", user.getUsername()).one();
        if(one != null){
            return Result.fail("用户名已被注册");
        }
        boolean isSuccess = save(user);
        if(!isSuccess){
            return Result.fail("注册失败");
        }




        return Result.ok("注册成功！请返回登录");
    }

    @Override
    public Result loginBytoken(String token) {
        String id = stringRedisTemplate.opsForValue().get("login:token:" + token);

        if(StrUtil.isBlank(id)){
            return Result.fail("登录信息验证失败");
        }

        User user = getById(id);

        System.out.println(user);

        UserDTO userDTO = BeanUtil.copyProperties(user, UserDTO.class);

        return Result.ok(userDTO);
    }
}
