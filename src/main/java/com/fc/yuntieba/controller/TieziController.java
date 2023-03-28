package com.fc.yuntieba.controller;


import com.fc.yuntieba.dto.Result;
import com.fc.yuntieba.dto.TieziDTO;
import com.fc.yuntieba.entity.Tiezi;
import com.fc.yuntieba.service.ITieziService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 贴子 前端控制器
 * </p>
 *
 * @author lqy
 * @since 2023-03-21
 */
@CrossOrigin
@RestController
@RequestMapping("/tiezi")
public class TieziController {
    @Resource
    private ITieziService tieziService;
    @PostMapping("/publish")
    public Result publish(@RequestBody Tiezi tiezi){
        return tieziService.publish(tiezi);
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody TieziDTO tieziDTO){
        return tieziService.delete(tieziDTO);
    }
    //关注的贴吧的贴子，按时间排序
    @GetMapping("/myfollow/{id}/{page}")
    public Result myfollow(@PathVariable int id,@PathVariable int page){
        return tieziService.myfollow(id,page);
    }
    //随机贴子
    @GetMapping("/random")
    public Result random(){
        return tieziService.random();
    }



}
