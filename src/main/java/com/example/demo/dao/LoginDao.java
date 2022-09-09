package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.LoginEntity;
import org.mapstruct.Mapper;

@Mapper
public interface LoginDao extends BaseMapper<LoginEntity> {
}
