package com.fc.yuntieba.service;

import com.fc.yuntieba.dto.LoginFormDTO;
import com.fc.yuntieba.dto.Result;
import com.fc.yuntieba.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author lqy
 * @since 2023-03-21
 */
public interface IUserService extends IService<User> {

    Result login(LoginFormDTO loginFormDTO);

    Result register(User user);

    Result loginBytoken(String token);
}
