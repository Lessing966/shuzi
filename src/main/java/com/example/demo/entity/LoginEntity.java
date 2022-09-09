package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("sys_login")
public class LoginEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "所属平台id")
    private Integer pId;

    @TableField(exist = false)
    private String pname;

    @ApiModelProperty(value = "角色")
    private Integer role;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "账户")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "备注")
    private String note;

    @ApiModelProperty(value = "页号")
    @TableField(exist = false)
    private int pageNumber;

    @ApiModelProperty(value = "页面条数")
    @TableField(exist = false)
    private int pageSize;
}
