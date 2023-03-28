package com.fc.yuntieba.controller;


import com.fc.yuntieba.dto.LoginFormDTO;
import com.fc.yuntieba.dto.Result;
import com.fc.yuntieba.entity.User;
import com.fc.yuntieba.service.IUserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author lqy
 * @since 2023-03-21
 */


@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;
    @PostMapping("/login")
    public Result login(@RequestBody LoginFormDTO loginFormDTO){
        return userService.login(loginFormDTO);
    }

    @GetMapping("/loginBytoken/{token}")
    public Result loginBytoken(@PathVariable  String token){
        return userService.loginBytoken(token);
    }

    @PostMapping("/register")
    public Result login(@RequestBody User user){
        return userService.register(user);
    }

}
