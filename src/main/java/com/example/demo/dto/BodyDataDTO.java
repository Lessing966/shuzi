package com.example.demo.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


@Data
@Api(value = "请求参数",tags = "请求参数实体")
public class BodyDataDTO {

    @ApiModelProperty(value = "platformId",required = true)
    private String platformId;

    @ApiModelProperty(value = "data",required = true)
    private String data;

    private Date createTime;

}
