package com.example.demo.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.dto.DataListDTO;
import com.example.demo.entity.DateEntity;
import com.example.demo.entity.LoginEntity;
import com.example.demo.entity.PlatfromEntity;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface WebDao {

    List<DateEntity> selectDataList(Page<DateEntity> page, @Param("dto") DataListDTO dto);

    List<PlatfromEntity> selectPlatfrom(Page<PlatfromEntity> page,@Param("dto") PlatfromEntity dto);

    List<LoginEntity> selectPersonList(Page<LoginEntity> page,@Param("dto")  LoginEntity entity);

}
