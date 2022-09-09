package com.example.demo.config;



import com.example.demo.common.JwtUtils;
import com.example.demo.common.R;
import com.example.demo.common.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 拦截器  验证Token
 */
//@Component
public class JwtAuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        R r = new R();
        PrintWriter out = null;
       try {
           String token = request.getHeader("token");
           if(StringUtils.isEmpty(token)){
               r.put("code","401");
               r.put("msg","Token为空");
               out = response.getWriter();
               out.append(r.toString());
               return false;
           }
           String userid = JwtUtils.getClaimByName(token, "userid").asString();
           if(StringUtils.isEmpty(userid)){
               return false;
           }
           String tk = redisUtils.get(userid + ":token");
           if(StringUtils.isEmpty(tk)){
               r.put("code","401");
               r.put("msg","Token失效");
               out = response.getWriter();
               out.append(r.toString());
               return false;
           }
       }catch (Exception e){
           e.printStackTrace();
           return false;
       }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

}