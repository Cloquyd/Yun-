package com.fc.yuntieba.service;

import com.fc.yuntieba.dto.Result;
import com.fc.yuntieba.dto.TieziDTO;
import com.fc.yuntieba.entity.Tiezi;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 贴子 服务类
 * </p>
 *
 * @author lqy
 * @since 2023-03-21
 */
public interface ITieziService extends IService<Tiezi> {

    Result publish(Tiezi tiezi);

    Result delete(TieziDTO tieziDTO);


    Result myfollow(int id,int page);

    Result random();
}
