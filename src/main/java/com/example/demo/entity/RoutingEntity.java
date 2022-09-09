package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_routing")
public class RoutingEntity {
    private Integer id;
    private String note;
    private Integer uid;
}
