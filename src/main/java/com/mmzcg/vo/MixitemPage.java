package com.mmzcg.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MixitemPage<T> {

    @ApiModelProperty("总条数")
    private long total = 0;

    @ApiModelProperty(value = "每页显示条数",notes = "默认 10")
    private long size = 10;

    @ApiModelProperty(value = "当前页",notes = "默认 1")
    private long current = 1;

    @ApiModelProperty("搜索条件参数")
    private T searchParams;

}
