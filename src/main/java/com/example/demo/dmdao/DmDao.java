package com.example.demo.dmdao;

import com.example.demo.entity.DMDataEntity;
import com.example.demo.entity.DateEntity;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.Date;
import java.util.Map;

@Mapper
public interface DmDao {

    String queryForMap(@Param("name") String name, @Param("date") String date);

    void insertDmdata(@Param("entity") DateEntity dateEntity);

    Integer getDmLimit();

}