package com.fc.yuntieba.service.impl;

import cn.hutool.core.util.StrUtil;
import com.fc.yuntieba.dto.Result;
import com.fc.yuntieba.entity.FollowUserTieba;
import com.fc.yuntieba.entity.Tieba;
import com.fc.yuntieba.mapper.FollowUserTiebaMapper;
import com.fc.yuntieba.service.IFollowUserTiebaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fc.yuntieba.service.ITiebaService;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户关注贴吧 服务实现类
 * </p>
 *
 * @author lqy
 * @since 2023-03-21
 */
@Service
public class FollowUserTiebaServiceImpl extends ServiceImpl<FollowUserTiebaMapper, FollowUserTieba> implements IFollowUserTiebaService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private ITiebaService tiebaService;

    @Override
    public Result follow(FollowUserTieba followUserTieba) {

        Tieba tieba = tiebaService.getById(followUserTieba.getTiebaId());

        if(tieba == null){
            return Result.fail("关注失败，贴吧不存在");
        }
        String key = "follow:tieba:" + followUserTieba.getUserId();

        Boolean isFollowed = stringRedisTemplate.opsForSet().isMember(key, StrUtil.toString(tieba.getId()));
        if(isFollowed){
            return Result.ok("已经关注了！");
        }

        boolean isSuccess = save(followUserTieba);
        if(!isSuccess){
            return Result.fail("关注失败");
        }
        stringRedisTemplate.opsForSet().add("follow:tieba:" + followUserTieba.getUserId(), StrUtil.toString(followUserTieba.getTiebaId()));

        return Result.ok("关注成功");
    }
}
