package com.fc.yuntieba.dto;

import lombok.Data;

@Data
public class CommentDTO {

    private Integer userId;

    private Integer tieziId;

    private String content;

    private String nickname;
}
