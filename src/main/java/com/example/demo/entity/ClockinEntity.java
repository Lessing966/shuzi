package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@TableName("sys_clockin")
public class ClockinEntity {
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

    @ApiModelProperty(value = "设备编号")
    private String equipmentNumber;

    @ApiModelProperty(value = "刷卡时间")
    private String codeScanningTime;

    @ApiModelProperty(value = "人员通行方式：人脸识别、身份证、随申码")
    private String entryType;

    @ApiModelProperty(value = "数据类型，白名单、访客")
    private String intoType;

    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = "身份证号")
    private String idNumber;

    @ApiModelProperty(value = "手机号码")
    private String phoneNumber;

    private Date createTime;
}
