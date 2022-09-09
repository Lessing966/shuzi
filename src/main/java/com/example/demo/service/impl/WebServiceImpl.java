package com.example.demo.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.RandomUtil;
import com.example.demo.dao.PlatfromDao;
import com.example.demo.dao.WebDao;
import com.example.demo.dto.DataListDTO;
import com.example.demo.entity.DateEntity;
import com.example.demo.entity.PlatfromEntity;
import com.example.demo.service.WebService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class WebServiceImpl implements WebService {

    @Autowired
    WebDao webDao;

    @Autowired
    PlatfromDao platfromDao;

    @Override
    public Page<DateEntity> selectList(DataListDTO dto) {
        Page<DateEntity> page = new Page<>(dto.getPageNumber(),dto.getPageSize());
        List<DateEntity> list = webDao.selectDataList(page,dto);
        return page.setRecords(list);
    }

    @Override
    public Page<PlatfromEntity> selectAllPlatform(PlatfromEntity dto) {
        Page<PlatfromEntity> page = new Page<>(dto.getPageNumber(),dto.getPageSize());
        List<PlatfromEntity> platfromEntities = webDao.selectPlatfrom(page,dto);
        return page.setRecords(platfromEntities);
    }

    @Override
    public List<DateEntity> outExcel(DataListDTO dto) {
        return webDao.selectDataList(null,dto);
    }

    @Override
    public void AddPlatform(PlatfromEntity entity) {
        //1.UUID生成32位数
        String uuid32 = UUID.randomUUID().toString().replace("-", "");
        //2.然后截取前面或后面16位
        String uuid16 = uuid32.substring(0, 16);
        entity.setKy(uuid32);
        entity.setIv(uuid16);
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        entity.setId(RandomUtil.generateByShuffle());
        platfromDao.insert(entity);
    }

    @Override
    public void UpdatePlatform(PlatfromEntity entity) {
        entity.setUpdateTime(new Date());
        platfromDao.updateById(entity);
    }

    @Override
    public void DeletePlatform(Integer id) {
        platfromDao.deleteById(id);
    }

}