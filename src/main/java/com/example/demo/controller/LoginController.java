package com.example.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.example.demo.common.JwtUtils;
import com.example.demo.common.R;
import com.example.demo.common.RedisUtils;
import com.example.demo.entity.LoginEntity;
import com.example.demo.entity.RoutingEntity;
import com.example.demo.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/sys")
@Api(value = "登录",tags = "系统人员Controller")
public class LoginController {

    @Autowired
    LoginService loginService;
    @Autowired
    RedisUtils redisUtils;


    @ApiOperation("login")
    @PostMapping("/login")
    public R login(@RequestBody LoginEntity entity){
        LoginEntity login = loginService.selectByUserName(entity.getUsername());
        if(null == login){
            return R.error(false,404,"没有此用户");
        }
        if(!entity.getPassword().equals(login.getPassword())){
            return R.error(false,404,"密码错误");
        }
        //登录成功
        String token = JwtUtils.createToken(String.valueOf(login.getId()), login.getUsername());
        if(!StringUtils.isEmpty(token)){
            //存入redis  默认24小时
            redisUtils.set(login.getId()+":token",token);
        }
        return R.ok().put("token",token)
                .put("user",login);
    }

    @ApiOperation("获取权限路由")
    @PostMapping("/getrouting")
    public R getrouting(HttpServletRequest request){
        JSONArray objects =new JSONArray();
        String token = request.getHeader("token");
        if(StringUtils.isEmpty(token)){
            return R.error(false,404,"无token");
        }
        String userid = JwtUtils.getClaimByName(token, "userid").asString();
        if(!StringUtils.isEmpty(userid)){
            RoutingEntity routingEntity = loginService.selectOne(userid);
            String note = routingEntity.getNote();
            objects = JSONArray.parseArray(note);
        }
        return R.ok().put("data",objects);
    }


    @ApiOperation("用户列表")
    @PostMapping("/personList")
    public R personList(@RequestBody LoginEntity entity){
        return R.ok().put("data",loginService.selectList(entity));
    }

    @ApiOperation("新增用户")
    @PostMapping("/personAdd")
    public R personAdd(@RequestBody LoginEntity entity){
        loginService.personAdd(entity);
        return R.ok();
    }

    @ApiOperation("修改用户")
    @PostMapping("/personUpdate")
    public R personUpdate(@RequestBody LoginEntity entity){
        loginService.personUpdate(entity);
        return R.ok();
    }


    @ApiOperation("删除用户")
    @GetMapping("/personDelete")
    public R personDelete(Integer id){
        loginService.personDelete(id);
        return R.ok();
    }

}