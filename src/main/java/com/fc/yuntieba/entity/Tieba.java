package com.fc.yuntieba.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 贴吧
 * </p>
 *
 * @author lqy
 * @since 2023-03-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Tieba implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private int masterId;

    @TableField(exist = false)
    private Boolean isFollowed;


}
