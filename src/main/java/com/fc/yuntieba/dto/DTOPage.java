package com.fc.yuntieba.dto;

import com.fc.yuntieba.entity.Tiezi;
import lombok.Data;

import java.util.List;

@Data
public class DTOPage {
    private int pages;

    private int current;

    private Object items ;
}
