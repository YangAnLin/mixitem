package com.mmzcg.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class GameListOuput {

    @ApiModelProperty("用户名")
    private String account;

    @ApiModelProperty("层级ID")
    private Integer levelId;

    @ApiModelProperty("状态")
    private Integer running;

    @ApiModelProperty("注册IP")
    private String registerIp;

    @ApiModelProperty("注册开始时间")
    private Date registerStartDate;

    @ApiModelProperty("注册结束时间")
    private Date registerEndDate;

    @ApiModelProperty("登录开始时间")
    private Date loginStartDate;

    @ApiModelProperty("注册结束时间")
    private Date loginEndDate;

    @ApiModelProperty("首充开始时间")
    private Date firstChangeStart;

    @ApiModelProperty("首充结束时间")
    private Date firstChangeEnd;

    @ApiModelProperty("首充结束时间")
    private Integer registerMachine;

    @ApiModelProperty(value = "注册方法",notes = "1.账号,2:手机号,3:微信,4:其它第三方")
    private Integer registerType;

    @ApiModelProperty(value = "业主ID",hidden = true)
    private Integer parentAccountId;

}
