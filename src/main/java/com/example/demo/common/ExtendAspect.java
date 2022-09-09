package com.example.demo.common;



import com.alibaba.fastjson.JSONObject;
import com.example.demo.dto.BodyDataDTO;
import com.example.demo.entity.DMDataEntity;
import com.example.demo.entity.DateEntity;
import com.example.demo.entity.PlatfromEntity;
import com.example.demo.service.GuardDataService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

import java.util.Map;


@Slf4j
@Aspect
@Component
public class ExtendAspect {

    @Autowired
    GuardDataService guardDataService;

    // 配置织入点
    @Pointcut("@annotation(com.example.demo.common.Extend)")
    public void PointCut()
    {
    }


    /**
     * 处理完请求后执行
     *
     * @param
     */
    @AfterReturning(pointcut = "@annotation(controllerLog)")
    public void doAfterReturning(JoinPoint joinPoint, Extend controllerLog)
    {
        handleLog(joinPoint,controllerLog);
    }


    @Async("asyncServiceExecutor")
    protected void handleLog(final JoinPoint joinPoint, Extend controllerLog)
    {
        Object args = joinPoint.getArgs();
        if (!StringUtils.isEmpty(args))
        {
            String params = argsArrayToString(joinPoint.getArgs());
            BodyDataDTO bodyDataDTO = JSONObject.parseObject(params, BodyDataDTO.class);
            log.info("切面获取请求的参数："+bodyDataDTO.toString());
            PlatfromEntity platfrom = guardDataService.selectPlatform(bodyDataDTO.getPlatformId());
            String decode = DecodeUtils.decode(platfrom.getKy(), platfrom.getIv(), bodyDataDTO.getData());
            System.out.println(decode);
            DateEntity dateEntity = JSONObject.parseObject(decode, DateEntity.class);
            log.info("data的数据："+dateEntity.toString());
            //入库 达梦数据库中
            dateEntity.setJH_OPER_TIME(System.currentTimeMillis()/1000);
            dateEntity.setJH_OPER_TYPE("I");
            dateEntity.setPId(Integer.valueOf(bodyDataDTO.getPlatformId()));
            guardDataService.insertDm(dateEntity);
        }

    }

    private String argsArrayToString(Object[] paramsArray)
    {
        String params = "";
        if (paramsArray != null && paramsArray.length > 0)
        {
            for (Object o : paramsArray)
            {
                if (!StringUtils.isEmpty(o) && !isFilterObject(o))
                {
                    try
                    {
                        Object jsonObj = JSONObject.toJSONString(o);
                        params += jsonObj.toString() + " ";
                    }
                    catch (Exception e)
                    {
                    }
                }
            }
        }
        return params.trim();
    }



    @SuppressWarnings("rawtypes")
    public boolean isFilterObject(final Object o)
    {
        Class<?> clazz = o.getClass();
        if (clazz.isArray())
        {
            return clazz.getComponentType().isAssignableFrom(MultipartFile.class);
        }
        else if (Collection.class.isAssignableFrom(clazz))
        {
            Collection collection = (Collection) o;
            for (Object value : collection)
            {
                return value instanceof MultipartFile;
            }
        }
        else if (Map.class.isAssignableFrom(clazz))
        {
            Map map = (Map) o;
            for (Object value : map.entrySet())
            {
                Map.Entry entry = (Map.Entry) value;
                return entry.getValue() instanceof MultipartFile;
            }
        }
        return o instanceof MultipartFile || o instanceof HttpServletRequest || o instanceof HttpServletResponse
                || o instanceof BindingResult;
    }
}
