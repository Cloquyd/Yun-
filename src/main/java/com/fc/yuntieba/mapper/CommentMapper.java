package com.fc.yuntieba.mapper;

import com.fc.yuntieba.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 评论 Mapper 接口
 * </p>
 *
 * @author lqy
 * @since 2023-03-21
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}
