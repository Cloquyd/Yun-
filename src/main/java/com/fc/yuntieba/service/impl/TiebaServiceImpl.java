package com.fc.yuntieba.service.impl;

import cn.hutool.core.util.StrUtil;
import com.fc.yuntieba.dto.Result;
import com.fc.yuntieba.entity.Tieba;
import com.fc.yuntieba.entity.Tiezi;
import com.fc.yuntieba.mapper.TiebaMapper;
import com.fc.yuntieba.service.ITiebaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fc.yuntieba.service.ITieziService;
import com.fc.yuntieba.service.IUserService;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 贴吧 服务实现类
 * </p>
 *
 * @author lqy
 * @since 2023-03-21
 */
@Service
public class TiebaServiceImpl extends ServiceImpl<TiebaMapper, Tieba> implements ITiebaService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private ITieziService tieziService;

    @Resource
    private IUserService userService;



    @Override
    public Result create(Tieba tieba) {
        save(tieba);
        return Result.ok("创建成功");
    }

    @Override
    public Result delete(Tieba tieba) {
        Tieba tieba1 = query().select("master_id").eq("id", tieba.getId()).one();
        if(tieba1.getMasterId() != tieba.getMasterId()){
            return Result.fail("删除失败，不是你的贴吧！");
        }
        boolean b = removeById(tieba.getId());
        if(!b){
            return Result.fail("删除失败");
        }
        return Result.ok("删除成功");
    }

    @Override
    public Result getMyFollow(int id) {
        String key = "follow:tieba:" + id;
        //根据userId查所有关注的贴吧id的集合
        Set<String> tiebas = stringRedisTemplate.opsForSet().members(key);
        List<Integer> ids = tiebas.stream()
                .map(sid -> Integer.parseInt(sid))
                .collect(Collectors.toList());
        //没有关注的吧
        if(ids.isEmpty()){
            return Result.ok();
        }

        List<Tieba> allTieba = query().in("id", ids).list();

        return Result.ok(allTieba);
    }

    @Override
    public Result search(int userId, String keyWord) {
        List<Tieba> tiebaList = query().like("name", keyWord).list();
        if(tiebaList.isEmpty()){
            return Result.ok();
        }

        String key = "follow:tieba:" + userId;

        List<Tieba> tiebas = tiebaList.stream().map(new Function<Tieba, Tieba>() {
            @Override
            public Tieba apply(Tieba tieba) {
                tieba.setIsFollowed(stringRedisTemplate.opsForSet().isMember(key, StrUtil.toString(tieba.getId())));
                return tieba;
            }
        }).collect(Collectors.toList());


        return Result.ok(tiebas);
    }

    @Override
    public Result getAll(int tiebaId) {
        List<Tiezi> tiezis = tieziService.query().eq("tieba_id", tiebaId).list();
        if(tiezis.isEmpty()){
            return Result.ok();
        }
        tiezis.forEach(tiezi -> tiezi.setNickName(userService.getById(tiezi.getUserId()).getNickname()));
        return Result.ok(tiezis);
    }
}
