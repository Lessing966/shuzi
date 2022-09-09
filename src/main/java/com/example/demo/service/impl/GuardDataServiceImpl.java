package com.example.demo.service.impl;



import com.example.demo.dao.ClockinDao;
import com.example.demo.dao.GuardDao;
import com.example.demo.dao.PlatfromDao;
import com.example.demo.dao.WhiteListDao;
import com.example.demo.dmdao.DmDao;
import com.example.demo.entity.*;
import com.example.demo.service.GuardDataService;
import com.example.demo.service.WebService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@Slf4j
public class GuardDataServiceImpl implements GuardDataService {

    @Autowired
    PlatfromDao platfromDao;
    @Autowired
    GuardDao guardDao;

    @Autowired
    WhiteListDao whiteListDao;

    @Autowired
    ClockinDao clockinDao;

    @Autowired
    WebService webService;

    @Autowired
    DmDao dmDao;

    @Override
    public PlatfromEntity selectPlatform(String platformId) {
        return platfromDao.selectById(platformId);
    }

    @Override
//    @Async("asyncServiceExecutor")
    public void insert(DateEntity dateEntity) {
        guardDao.insert(dateEntity);
    }

    @Override
    public void dataWhitelist(WhiteListEntity whiteList) {
        whiteListDao.insert(whiteList);
    }

    @Override
    public void dataClockinlist(ClockinEntity clockin) {
        clockinDao.insert(clockin);
    }

    @Override
    public void insertDm(DateEntity dateEntity) {
        dmDao.insertDmdata(dateEntity);
    }

    @Override
    public String getCron() {
        return guardDao.selectCron();
    }

    @Override
    public Integer getDmLimit() {
        return dmDao.getDmLimit();
    }

    @Override
    public List<DateEntity> selectMysqlData(Integer dataid) {
        return guardDao.selectMysqlData(dataid);
    }

    @Override
    public List<DateEntity> selectMysqlDatad(Integer dataid) {
        return guardDao.selectMysqlDatad(dataid);
    }


}
