package com.fc.yuntieba.service;

import com.fc.yuntieba.dto.Result;
import com.fc.yuntieba.entity.FollowUserTieba;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户关注贴吧 服务类
 * </p>
 *
 * @author lqy
 * @since 2023-03-21
 */
public interface IFollowUserTiebaService extends IService<FollowUserTieba> {

    Result follow(FollowUserTieba followUserTieba);
}
