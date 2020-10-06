package com.mmzcg.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class MixitemResponsePage<T> {

    @ApiModelProperty("总条数")
    private long total = 0;

    @ApiModelProperty(value = "每页显示条数",notes = "默认 10")
    private long size = 10;

    @ApiModelProperty(value = "当前页",notes = "默认 1")
    private long current = 1;

    @ApiModelProperty(value = "总页数",notes = "默认 0")
    private long pages;

    @ApiModelProperty("搜索条件参数")
    private List<T> data;
    
}
