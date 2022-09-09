package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface GuardDataService {

    PlatfromEntity selectPlatform(String platformId);


    void insert(DateEntity dateEntity);

    void dataWhitelist(WhiteListEntity whiteList);

    void dataClockinlist(ClockinEntity clockin);

    void insertDm(DateEntity dateEntity);

    String getCron();

    Integer getDmLimit();

    List<DateEntity> selectMysqlData(Integer dataid);


    List<DateEntity> selectMysqlDatad(Integer data_id);

}

