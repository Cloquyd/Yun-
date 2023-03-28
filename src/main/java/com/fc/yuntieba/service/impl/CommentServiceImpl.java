package com.fc.yuntieba.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.fc.yuntieba.dto.CommentDTO;
import com.fc.yuntieba.dto.Result;
import com.fc.yuntieba.entity.Comment;
import com.fc.yuntieba.mapper.CommentMapper;
import com.fc.yuntieba.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author lqy
 * @since 2023-03-21
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {


    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public Result comment(CommentDTO commentDTO) {

        Comment comment = BeanUtil.copyProperties(commentDTO, Comment.class);
        boolean isSuccess = save(comment);
        if(!isSuccess){
            return Result.fail("评论失败");
        }

        String key = "yuntieba:comment:" + commentDTO.getTieziId();
        stringRedisTemplate.opsForZSet().add(key, JSONUtil.toJsonStr(commentDTO),System.currentTimeMillis());

        return Result.ok("评论成功");
    }
}
