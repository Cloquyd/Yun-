package com.fc.yuntieba.controller;


import com.fc.yuntieba.dto.Result;
import com.fc.yuntieba.entity.Tieba;
import com.fc.yuntieba.service.ITiebaService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 贴吧 前端控制器
 * </p>
 *
 * @author lqy
 * @since 2023-03-21
 */
@CrossOrigin
@RestController
@RequestMapping("/tieba")
public class TiebaController {
    @Resource
    private ITiebaService tiebaService;

    @PostMapping("/create")
    public Result create(@RequestBody Tieba tieba){
        return tiebaService.create(tieba);
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody Tieba tieba){
        return tiebaService.delete(tieba);
    }

    //关注的贴吧
    @GetMapping("/myfollow/{id}")
    public Result myFollow(@PathVariable int id){
        return tiebaService.getMyFollow(id);
    }

    //搜索贴吧
    //没做分页功能，数据太多可能会很卡
    @GetMapping("/search/{userId}/{keyWord}")
    public Result search(@PathVariable int userId,@PathVariable String keyWord){
        return tiebaService.search(userId,keyWord);
    }

    //进贴吧
    //未分页
    @GetMapping("/{tiebaId}")
    public Result inTieba(@PathVariable int tiebaId){
        return tiebaService.getAll(tiebaId);
    }
}
