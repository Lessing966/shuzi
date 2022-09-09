package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.example.demo.entity.DateEntity;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper
public interface GuardDao extends BaseMapper<DateEntity> {

    String selectCron();

    List<DateEntity> selectMysqlData(@Param("dataid") Integer dataid);

    List<DateEntity> selectMysqlDatad(Integer dataid);

}
