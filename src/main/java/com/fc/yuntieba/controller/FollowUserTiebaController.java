package com.fc.yuntieba.controller;


import com.fc.yuntieba.dto.Result;
import com.fc.yuntieba.entity.FollowUserTieba;
import com.fc.yuntieba.service.IFollowUserTiebaService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 用户关注贴吧 前端控制器
 * </p>
 *
 * @author lqy
 * @since 2023-03-21
 */
@CrossOrigin
@RestController
@RequestMapping("/follow")
public class FollowUserTiebaController {
    @Resource
    private IFollowUserTiebaService followUserTiebaService;

    @PostMapping("/tieba")
    public Result followTieba(@RequestBody FollowUserTieba followUserTieba){
        return followUserTiebaService.follow(followUserTieba);
    }
}
