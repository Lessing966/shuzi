package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@TableName("sys_whitelist")
@Api("白名单数据")
public class WhiteListEntity {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "平台id")
    private Integer pId;

    @ApiModelProperty(value = "序列号")
    private String serialNo;

    @ApiModelProperty(value = "场所码")
    private String account;

    @ApiModelProperty(value = "场所名称")
    private String sceneAccount;

    @ApiModelProperty(value = "地址")
    private String constructionAddress;

    @ApiModelProperty(value = "设备编号")
    private String equipmentId;

    @ApiModelProperty(value = "人员姓名")
    private String name;

    @ApiModelProperty(value = "身份证号码")
    private String idCardNumber;

    @ApiModelProperty(value = "手机号码")
    private String phoneNumber;

    @ApiModelProperty(value = "进场日期")
    private Date entryTime;

    @ApiModelProperty(value = "退场日期")
    private Date exitTime;

    @ApiModelProperty(value = "人员状态：新增、修改、删除")
    private String state;

//    @TableField(exist = false)
    private Date createTime;

}
