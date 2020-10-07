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
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("roles")
@ApiModel(value="Roles对象", description="角色表")
public class Roles extends Model<Roles> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("name")
    private String name;

    @ApiModelProperty(value = "上级")
    @TableField("parent_id")
    private Integer parentId;

    @ApiModelProperty(value = "厅主ID")
    @TableField("owen_parent_id")
    private Integer owenParentId;

    @TableField(exist = false)
    private List<Roles> children;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
