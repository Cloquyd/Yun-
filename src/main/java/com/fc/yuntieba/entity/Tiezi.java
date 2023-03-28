package com.fc.yuntieba.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 贴子
 * </p>
 *
 * @author lqy
 * @since 2023-03-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Tiezi implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private Integer tiebaId;

    @TableField(exist = false)
    private String tiebaName;

    @TableField(exist = false)
    private String nickName;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;


}
