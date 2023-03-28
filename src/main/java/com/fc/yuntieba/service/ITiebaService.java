package com.fc.yuntieba.service;

import com.fc.yuntieba.dto.Result;
import com.fc.yuntieba.entity.Tieba;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 贴吧 服务类
 * </p>
 *
 * @author lqy
 * @since 2023-03-21
 */
public interface ITiebaService extends IService<Tieba> {


    Result create(Tieba tieba);

    Result delete(Tieba tieba);

    Result getMyFollow(int id);

    Result search(int userId,String keyWord);

    Result getAll(int tiebaId);
}
