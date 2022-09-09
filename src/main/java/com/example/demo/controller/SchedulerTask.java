package com.example.demo.controller;

import com.example.demo.entity.DateEntity;
import com.example.demo.service.GuardDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class SchedulerTask {

    @Autowired
    GuardDataService guardDataService;


    /**
     * 定时向达梦数据库写入数据 定时任务接口
     * @ApiOperation("insertdmdata")
     * @PostMapping("/insertdmdata")
     * 0 0 20 * * ? 每晚8点
     */
    @Scheduled(cron="0 0 20 * * ?")
    public void insertdmdata (){
        //查询达梦数据库中最后一条数据的id 如果id为空就将mysql库中的所有数据都写入达梦
        //如果有 将mysql库中 大于 id数值的所有数据 写入达梦库
        Integer DATA_ID = guardDataService.getDmLimit();
        if(null == DATA_ID || DATA_ID ==0){
            log.info("达梦DATA_ID：为空");
            List<DateEntity> mydata =  guardDataService.selectMysqlData(DATA_ID);
            if(mydata.size()!=0){
                for(DateEntity d : mydata){
                    d.setDATA_ID(d.getId());
                    d.setJH_OPER_TIME(System.currentTimeMillis());
                    d.setJH_OPER_TYPE("I");
                    guardDataService.insertDm(d);
                }
            }
        }else {
            log.info("达梦DATA_ID："+DATA_ID);
            List<DateEntity> mydata =  guardDataService.selectMysqlDatad(DATA_ID);
            if(mydata.size()!=0){
                for(DateEntity d : mydata){
                    d.setJH_OPER_TIME(System.currentTimeMillis());
                    d.setJH_OPER_TYPE("I");
                    d.setDATA_ID(d.getId());
                    guardDataService.insertDm(d);
                }
            }
        }
    }


}
