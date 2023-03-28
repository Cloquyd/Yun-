package com.fc.yuntieba.service;

import com.fc.yuntieba.dto.CommentDTO;
import com.fc.yuntieba.dto.Result;
import com.fc.yuntieba.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 评论 服务类
 * </p>
 *
 * @author lqy
 * @since 2023-03-21
 */
public interface ICommentService extends IService<Comment> {

    Result comment(CommentDTO commentDTO);
}
