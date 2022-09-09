package com.example.demo.service;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.dto.DataListDTO;
import com.example.demo.entity.DateEntity;
import com.example.demo.entity.PlatfromEntity;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface WebService {
    Page<DateEntity> selectList(DataListDTO dto);

    Page<PlatfromEntity> selectAllPlatform(PlatfromEntity entity);

    List<DateEntity> outExcel(DataListDTO dto);

    void AddPlatform(PlatfromEntity entity);

    void UpdatePlatform(PlatfromEntity entity);

    void DeletePlatform(Integer id);

}

