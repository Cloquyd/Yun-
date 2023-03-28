package com.fc.yuntieba.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fc.yuntieba.dto.DTOPage;
import com.fc.yuntieba.dto.Result;
import com.fc.yuntieba.dto.TieziDTO;
import com.fc.yuntieba.entity.Tiezi;
import com.fc.yuntieba.mapper.TieziMapper;
import com.fc.yuntieba.service.ITiebaService;
import com.fc.yuntieba.service.ITieziService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 贴子 服务实现类
 * </p>
 *
 * @author lqy
 * @since 2023-03-21
 */
@Service
public class TieziServiceImpl extends ServiceImpl<TieziMapper, Tiezi> implements ITieziService {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    @Lazy
    private ITiebaService tiebaService;

    @Override
    public Result publish(Tiezi tiezi) {
        boolean isSuccess = save(tiezi);
        if (!isSuccess) {
            return Result.fail("发布失败");
        }

        stringRedisTemplate.opsForSet().add("yuntieba:tiezi", StrUtil.toString(tiezi.getId()));

        return Result.ok("发布成功");
    }

    @Override
    public Result delete(TieziDTO tieziDTO) {
        Tiezi tiezi = query().select("user_id").eq("id",tieziDTO.getId()).one();
        if(tiezi == null || tiezi.getUserId() != tieziDTO.getUserId()){
            return Result.fail("删除失败！");
        }
        boolean b = removeById(tieziDTO.getId());
        if(!b){
            return Result.fail("删除失败");
        }

        stringRedisTemplate.opsForSet().remove("yuntieba:tiezi",StrUtil.toString(tieziDTO.getId()));
        return Result.ok("删除成功");
    }

    @Override
    public Result myfollow(int id , int page) {
        String key = "follow:tieba:" + id;


        List<Integer> tiebaList = stringRedisTemplate.opsForSet().members(key)
                .stream().map(sid -> Integer.parseInt(sid))
                .collect(Collectors.toList());

//        System.out.println(tiebaList);

        if(tiebaList.isEmpty()){
            return Result.ok();
        }
        QueryWrapper<Tiezi> qw = new QueryWrapper<>();
        qw.in("tieba_id",tiebaList).orderByDesc("id");

        Page<Tiezi> tieziPage = new Page<>(page,5);
        page(tieziPage,qw);
        System.out.println(tieziPage.getTotal());

        DTOPage dtoPage = new DTOPage();
        dtoPage.setPages((int) tieziPage.getPages());
        dtoPage.setCurrent(page);
        dtoPage.setItems(tieziPage.getRecords());


        return Result.ok(dtoPage);
    }

    @Override
    public Result random() {
        List<Integer> ids = stringRedisTemplate.opsForSet().randomMembers("yuntieba:tiezi", 10L)
                .stream()
                .map(id -> Integer.parseInt(id))
                .distinct()
                .collect(Collectors.toList());
        System.out.println(ids);
        List<Tiezi> tieziList = query().in("id", ids).list();

        tieziList.forEach(tiezi -> {
            tiezi.setTiebaName(tiebaService.getById(tiezi.getTiebaId()).getName());
        });

        System.out.println(tieziList);
        return Result.ok(tieziList);
    }


}
