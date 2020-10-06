package com.mmzcg.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class MenuList {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "菜单名称")
    @TableField("menu_name")
    private String menuName;

    @ApiModelProperty(value = "菜单url")
    @TableField("menu_url")
    private String menuUrl;

    @ApiModelProperty(value = "菜单图标")
    @TableField("menu_icon")
    private String menuIcon;

    private List<MenuList> children;
}
