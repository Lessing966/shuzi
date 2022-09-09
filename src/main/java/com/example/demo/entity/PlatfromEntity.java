package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@TableName("sys_platform")
public class PlatfromEntity {

//    @TableId(type = IdType.AUTO)
    private String id;

    private String name;

    private String ky;

    private String iv;

    private Date createTime;

    private Date updateTime;

    private String note;

    @ApiModelProperty(value = "页号")
    @TableField(exist = false)
    private int pageNumber;

    @ApiModelProperty(value = "页面条数")
    @TableField(exist = false)
    private int pageSize;
}
