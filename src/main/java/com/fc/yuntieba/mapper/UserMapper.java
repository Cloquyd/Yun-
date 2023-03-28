package com.fc.yuntieba.mapper;

import com.fc.yuntieba.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author lqy
 * @since 2023-03-21
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
