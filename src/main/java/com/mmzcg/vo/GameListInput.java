package com.mmzcg.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GameListInput {

    @ApiModelProperty("层级ID")
    private Integer levelId;

    @ApiModelProperty(value = "状态",notes = "-1:全部")
    private Integer running = -1;

    @ApiModelProperty(value = "用户ID")
    private Integer id;

    @ApiModelProperty(value = "业主ID",hidden = true)
    private Integer parentAccountId;

}
