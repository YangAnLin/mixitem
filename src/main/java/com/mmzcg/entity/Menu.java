package com.mmzcg.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Transient;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("menu")
@ApiModel(value="Menu对象", description="")
public class Menu extends Model<Menu> {

    private static final long serialVersionUID = 1L;

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

    @ApiModelProperty(value = "上级id")
    @TableField("parent_id")
    private Integer parentId;

    @ApiModelProperty(value = "厅主ID")
    @TableField("owen_parent_id")
    private Integer owenParentId;

    @TableField(exist = false)
    private List<Menu> children;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
