package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.dao.LoginDao;
import com.example.demo.dao.RoutingDao;
import com.example.demo.dao.WebDao;
import com.example.demo.entity.LoginEntity;
import com.example.demo.entity.RoutingEntity;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginDao loginDao;

    @Autowired
    WebDao webDao;

    @Autowired
    RoutingDao routingDao;

    @Override
    public LoginEntity selectByUserName(String username) {
        return loginDao.selectOne(new QueryWrapper<LoginEntity>().eq("username",username));
    }

    @Override
    public Page<LoginEntity> selectList(LoginEntity entity) {
        Page<LoginEntity> page = new Page<>(entity.getPageNumber(),entity.getPageSize());
        List<LoginEntity> list = webDao.selectPersonList(page,entity);
        return page.setRecords(list);
    }

    @Override
    public void personAdd(LoginEntity entity) {
        loginDao.insert(entity);
    }

    @Override
    public void personUpdate(LoginEntity entity) {
        int i = loginDao.updateById(entity);
    }

    @Override
    public void personDelete(Integer id) {
        int i = loginDao.deleteById(id);
    }

    @Override
    public RoutingEntity selectOne(String userid) {
        return routingDao.selectOne(new QueryWrapper<RoutingEntity>().eq("uid", userid));
    }


}
