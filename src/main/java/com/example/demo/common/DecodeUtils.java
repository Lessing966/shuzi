package com.example.demo.common;


import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Component
public class DecodeUtils implements CommandLineRunner {


    @Autowired
    RedisUtils redisUtils;



    public static String decode(String ky,String iv,String datas){
        /**
         * Base64解码后 进行SM4解密
         * */
        byte[] decode = Base64.getDecoder().decode(datas);
        String SM4data = new String(decode);

        String data = null;
        try {
            data = Sm4Util2.uncoverMsg(ky, iv,SM4data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    @Override
    public void run(String... args) throws Exception {

       /* Map<String,Object> map = new HashMap<>();
        map.put("userName","admin");
        map.put("passWord","0454270b5a8f1ea49f51e24109ae2a151efe5c69c5fef97c6ecd4c3833dfecf0d6dc6ebbffc46301ee72b1f00d460d0e06e4b35e83c496ee18de495fcb4516477f49afd2f2463b731e29b672d002ef3639fddb68293c6d070c82428f8b647eee22cbebb99ba49d");

        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity("http://220.196.243.26:8082/ds/app/login", map, String.class);
        String msg = JSONObject.parseObject(stringResponseEntity.getBody()).getString("msg");
        redisUtils.set(msg,msg);*/


    }

}
