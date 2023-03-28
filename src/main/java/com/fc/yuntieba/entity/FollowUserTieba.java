package com.fc.yuntieba.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户关注贴吧
 * </p>
 *
 * @author lqy
 * @since 2023-03-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FollowUserTieba implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userId;

    private Integer tiebaId;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;


}
