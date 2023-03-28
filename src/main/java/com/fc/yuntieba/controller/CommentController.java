package com.fc.yuntieba.controller;


import com.fc.yuntieba.dto.CommentDTO;
import com.fc.yuntieba.dto.Result;
import com.fc.yuntieba.service.ICommentService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author lqy
 * @since 2023-03-21
 */
@CrossOrigin
@RestController
public class CommentController {
    @Resource
    private ICommentService commentService;
    @PostMapping("/comment")
    public Result comment(@RequestBody CommentDTO commentDTO){
        return commentService.comment(commentDTO);
    }
}
