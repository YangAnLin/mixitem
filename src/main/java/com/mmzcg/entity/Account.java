package com.mmzcg.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("account")
@ApiModel(value="Account对象")
public class Account extends Model<Account> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "注册方法")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "账号")
    @TableField("account")
    private String account;

    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "手机号")
    @TableField("mobile_phone")
    private String mobilePhone;

    @ApiModelProperty(value = "生日")
    @TableField("birthday")
    private Date birthday;

    @ApiModelProperty(value = "昵称")
    @TableField("nick_name")
    private String nickName;

    @ApiModelProperty(value = "真实姓名")
    @TableField("real_name")
    private String realName;

    @TableField("email")
    private String email;

    @TableField("qq")
    private String qq;

    @ApiModelProperty(value = "层级ID")
    @TableField("level_id")
    private Integer levelId;

    @ApiModelProperty(value = "状态-1:正常,2:冻结")
    @TableField("running")
    private String running;

    @ApiModelProperty(value = "登录次数")
    @TableField("login_count")
    private Integer loginCount;

    @ApiModelProperty(value = "最后一次登录时间")
    @TableField("login_last_time")
    private Date loginLastTime;

    @ApiModelProperty(value = "最后一次登录ip")
    @TableField("login_last__ip")
    private String loginLastIp;

    @ApiModelProperty(value = "最后一次登录地区")
    @TableField("login_last__area")
    private String loginLastArea;

    @ApiModelProperty(value = "多少天没有登录了")
    @TableField("login_no_day")
    private String loginNoDay;

    @ApiModelProperty(value = "注册ip")
    @TableField("register_ip")
    private String registerIp;

    @ApiModelProperty(value = "注册地区")
    @TableField("register_area")
    private String registerArea;

    @ApiModelProperty(value = "注册时间")
    @TableField("register_date")
    private Date registerDate;

    @ApiModelProperty(value = "注册中端-1:安卓,2:IOS,3:H5")
    @TableField("register_terminal")
    private String registerTerminal;

    @ApiModelProperty(value = "注册方法-1.账号,2:手机号,3:微信,4:其它第三方")
    @TableField("register_type")
    private String registerType;

    @ApiModelProperty(value = "是否是代理-1:是,2:不是")
    @TableField("is_agent")
    private Integer isAgent;

    @ApiModelProperty(value = "上级代理")
    @TableField("qm_account_id")
    private Integer qmAccountId;

    @ApiModelProperty(value = "业主ID")
    @TableField("parent_account_id")
    private Integer parentAccountId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
