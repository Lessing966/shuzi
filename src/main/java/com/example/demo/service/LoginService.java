package com.example.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.LoginEntity;
import com.example.demo.entity.RoutingEntity;

public interface LoginService {

    LoginEntity selectByUserName(String username);

    Page<LoginEntity> selectList(LoginEntity entity);

    void personAdd(LoginEntity entity);

    void personUpdate(LoginEntity entity);

    void personDelete(Integer id);

    RoutingEntity selectOne(String userid);

}
