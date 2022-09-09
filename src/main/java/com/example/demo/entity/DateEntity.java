package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.common.ExcelColumn;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Api(value = "",tags = "data")
@TableName("sys_data")
public class DateEntity implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField(exist = false)
    private Integer DATA_ID;

    @TableField(exist = false)
    private String JH_OPER_TYPE;

    @TableField(exist = false)
    private Long JH_OPER_TIME;

    private Integer pId;

    @ExcelColumn(value = "平台名称",col = 0)
    @TableField(exist = false)
    private String pname;

    @ExcelColumn(value = "数据序列号",col = 1)
    @ApiModelProperty(value = "数据序列号",required = true)
    private Integer serialNo;

    @ExcelColumn(value = "场所码",col = 2)
    @ApiModelProperty(value = "场所码",required = true)
    private String account;

    @ExcelColumn(value = "场所名称",col = 3)
    @ApiModelProperty(value = "场所名称",required = true)
    private String sceneAccount;

    @ExcelColumn(value = "核验设备",col = 4)
    @ApiModelProperty(value = "核验设备 Id",required = true)
    private String deviceId;

    @ExcelColumn(value = "核验时间",col = 5)
    @ApiModelProperty(value = "核验时间",required = true)
    private String checkTime;

    @ExcelColumn(value = "姓名",col = 6)
    @ApiModelProperty(value = "姓名",required = true)
    private String name;

    @ExcelColumn(value = "身份证号码",col = 7)
    @ApiModelProperty(value = " 身份证号码",required = true)
    private String idNumber;

    @ExcelColumn(value = "随申码状态",col = 8)
    @ApiModelProperty(value = "随申码状态 00:绿色 01:黄色 1:红色",required = true)
    private String ssmCode;

    @ExcelColumn(value = "核酸采样时间",col = 9)
    @ApiModelProperty(value = "核酸采样时间")
    private String nucleicTime;

    @ExcelColumn(value = "核酸结果",col = 10)
    @ApiModelProperty(value = "核酸结果 0:阴性 1:阳性 99:待复核",required = false)
    private Integer nucleicCode;

    @ExcelColumn(value = "当日场所核酸有效时长",col = 11)
    @ApiModelProperty(value = "当日场所核酸有效时长 默认 0 为不检测",required = false)
    private Integer nucleiTerm;

    @ExcelColumn(value = "抗原上报时间",col = 12)
    @ApiModelProperty(value = "抗原上报时间",required = false)
    private String antigenTime;

    @ExcelColumn(value = "抗原上报结果",col = 13)
    @ApiModelProperty(value = "抗原上报结果",required = false)
    private String antigenCode;

    @ExcelColumn(value = "当日场所抗原有效时长",col = 14)
    @ApiModelProperty(value = "当日场所抗原有效时长 默认 0 为不检测",required = false)
    private Integer antigenTerm;

    @ExcelColumn(value = "体温",col = 15)
    @ApiModelProperty(value = "体温",required = false)
    private BigDecimal temperature;

    @ExcelColumn(value = "体温阈值",col = 16)
    @ApiModelProperty(value = "体温阈值 默认 0 为不检测",required = false)
    private BigDecimal temperatureThreshold;

    private Date createTime;
}
