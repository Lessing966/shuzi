package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.*;
import com.example.demo.dto.BodyDataDTO;
import com.example.demo.entity.*;
import com.example.demo.service.GuardDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/third/v1")
@Api(value = "GuardDataController",tags = "采集数据Controller")
@Slf4j
public class GuardDataController {

    @Autowired
    GuardDataService guardDataService;

    /***
     * @param entity
     * @return
     */
    @PostMapping("/dataCollection")
    @ApiOperation("采集数据Api")
    public R dataCollection(@RequestBody BodyDataDTO entity){
        if(StringUtils.isEmpty(entity.getPlatformId())){
            return R.error(false,404,"平台ID必穿");
        }
        //根据平台id查询密钥和iv
        PlatfromEntity platfrom = guardDataService.selectPlatform(entity.getPlatformId());
        if(null == platfrom){
            return R.error(false,404,"平台ID为空");
        }
        try {
            /**
             * Base64解码后 进行SM4解密
             * */
            String decode = DecodeUtils.decode(platfrom.getKy(), platfrom.getIv(), entity.getData());
            log.info("采集数据Api-解密后明文："+decode);
            DateEntity dateEntity = JSONObject.parseObject(decode,DateEntity.class);
            log.info("接收对象实体："+dateEntity);
            if(!StringUtils.isEmpty(dateEntity.getName()) && !StringUtils.isEmpty(dateEntity.getAccount())
                    && !StringUtils.isEmpty(dateEntity.getIdNumber()) && !StringUtils.isEmpty(dateEntity.getDeviceId())){
                dateEntity.setPId(Integer.valueOf(entity.getPlatformId()));
                dateEntity.setCreateTime(new Date());
                guardDataService.insert(dateEntity);
            }
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error(false,500,"接收失败");
        }
    }



    @PostMapping("/dataWhitelist")
    @ApiOperation("工地白名单信息Api")
    public R dataWhitelist(@RequestBody BodyDataDTO entity){
        if(StringUtils.isEmpty(entity.getPlatformId())){
            return R.error(false,404,"平台ID为空");
        }
        //根据平台id查询密钥和iv
        PlatfromEntity platfrom = guardDataService.selectPlatform(entity.getPlatformId());
        if(null == platfrom){
            return R.error(false,404,"平台ID为空");
        }
        try {
            /**
             * Base64解码后 进行SM4解密
             * */
            String decode = DecodeUtils.decode(platfrom.getKy(), platfrom.getIv(), entity.getData());
            log.info("工地白名单信息Api-解密后明文："+decode);

            WhiteListEntity whiteList = JSONObject.parseObject(decode,WhiteListEntity.class);
            whiteList.setPId(Integer.valueOf(entity.getPlatformId()));
            whiteList.setCreateTime(new Date());
            guardDataService.dataWhitelist(whiteList);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error(false,500,"接收失败");
        }

    }

    @PostMapping("/dataClockinlist")
    @ApiOperation("人员考勤信息Api")
    public R dataClockinlist(@RequestBody BodyDataDTO entity){
        if(StringUtils.isEmpty(entity.getPlatformId())){
            return R.error(false,404,"平台ID为空");
        }
        //根据平台id查询密钥和iv
        PlatfromEntity platfrom = guardDataService.selectPlatform(entity.getPlatformId());
        if(null == platfrom){
            return R.error(false,404,"平台ID为空");
        }
        try {
            /**
             * Base64解码后 进行SM4解密
             * */
            String decode = DecodeUtils.decode(platfrom.getKy(), platfrom.getIv(), entity.getData());
            log.info("人员考勤信息Api-解密后明文："+decode);

            ClockinEntity clockin = JSONObject.parseObject(decode,ClockinEntity.class);
            clockin.setPId(Integer.valueOf(entity.getPlatformId()));
            clockin.setCreateTime(new Date());
            guardDataService.dataClockinlist(clockin);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error(false,500,"接收失败");
        }

    }

//    public static void main(String[] args) throws Exception {
//        //data数据
////        String data ="{\"account\":\"shsssmsh-ca003\",\"antigenCode\":\"阴性\",\"antigenTerm\":24,\"antigenTime\":\"2022-04-13 10:30:00\",\"checkTime\":\"2022-05-25 16:14:19\",\"deviceId\":\"xxkj_001\",\"idNumber\":\"**************0219\",\"name\":\"**宏\",\"nucleiTerm\":48,\"nucleicCode\":\"0\",\"nucleicTime\":\"2022-05-23 10:39:43\",\"sceneAccount\":\"信息科技测试场所\",\"serialNo\":8,\"ssmCode\":\"00\",\"temperature\":36.5,\"temperatureThreshold\":37.4}";
//
////        JSONObject jsonObject = JSONObject.parseObject(data);
////        System.out.println(jsonObject);
//
//        //白名单数据
//        String data="{\"serialNo\":\"001\",\"account\":\"001\",\"sceneAccount\":\"测试\",\"constructionAddress\":\"无锡市\",\n" +
//                "\"equipmentId\":\"111\",\"name\":\"里斯\",\"idCardNumber\":\"1111111111111\",\"phoneNumber\":\"11221221211\",\n" +
//                "\"entryTime\":\"2022-05-26 14:27:33\",\"exitTime\":\"2022-05-26 14:27:51\",\"state\":\"新增\"}";
//
//
//        //人员考勤数据
////        String data="{\"serialNo\":\"001\",\"account\":\"001\",\"sceneAccount\":\"测试\",\"equipmentNumber\":\"cs666\",\"codeScanningTime\":\"2022-05-26 14:27:51\",\"entryType\":\"人脸识别\",\"intoType\":\"访客\",\"name\":\"里斯\",\"idNumber\":\"2022-05-26 14:27:33\",\"phoneNumber\":\"11221221211\"}";
//
////        String data ="{\"test\":\"testData\"}";
//        String baolaixin = Sm4Util2.protectMsg("b38986559e3245f9b15bac37d1826035", "b38986559e3245f9", data);
//        System.out.println("密文为："+baolaixin);
//        String encodedStr = Base64.getEncoder().encodeToString(baolaixin.getBytes());
//        System.out.println("Base64加码后："+encodedStr);
////        byte[] decode = Base64.getDecoder().decode(encodedStr);
////        String s = new String(decode);
////        System.out.println("Base64解码后："+s);
//
//        String s1 = Sm4Util2.uncoverMsg("b38986559e3245f9b15bac37d1826035", "b38986559e3245f9", baolaixin);
//        System.out.println("解密后："+s1);
//    }

    public static void main(String[] args) {

        String datas= "y455d#7845456";

        //白名单数据
//        String datas="{\"serialNo\":\"001\",\"account\":\"001\",\"sceneAccount\":\"测试\",\"constructionAddress\":\"无锡市\",\n" +
//                "\"equipmentId\":\"111\",\"name\":\"里斯\",\"idCardNumber\":\"1111111111111\",\"phoneNumber\":\"11221221211\",\n" +
//                "\"entryTime\":\"2022-05-26 14:27:33\",\"exitTime\":\"2022-05-26 14:27:51\",\"state\":\"新增\"}";

        //人员考勤数据
//        String datas="{\"serialNo\":\"001\",\"account\":\"001\",\"sceneAccount\":\"测试\",\"equipmentNumber\":\"cs666\",\"codeScanningTime\":\"2022-05-26 14:27:51\",\"entryType\":\"人脸识别\",\"intoType\":\"访客\",\"name\":\"里斯\",\"idNumber\":\"2022-05-26 14:27:33\",\"phoneNumber\":\"11221221211\"}";

        //获取公私钥
//        SM2KeyPair sm2Keys = Sm2Utils.getSm2Keys(false);
//        System.out.println("公钥 ：" + sm2Keys.getPublicKey());
//        System.out.println("私钥 ：" + sm2Keys.getPrivateKey());

        //需要加密的数据
//        System.out.println("需要加密的data数据："+datas);
        //公钥加密，获取密文
        String encrypt = Sm2Utils.encrypt("045819445a3134d4efb07f7d50ce41ddc07dab0f29d2a75767156f3632cd06d34740714fe9373b71c432c3ea3f9ba54db2caaed8837f4692080f9e7c37bf03aa73", "123456");
        System.out.println("密文 ：" + encrypt);
        String s = Hex.toHexString(ByteUtils.fromHexString(encrypt));
//        String encodedStr = Base64.getEncoder().encodeToString(encrypt.getBytes());
        System.out.println("16进制密文 ：" + s);

        //私钥解密
//        String decrypt = Sm2Utils.decrypt(sm2Keys.getPrivateKey(), encrypt);
//        System.out.println("解密数据 : " + decrypt);

//        System.out.println("明文密文是否相同 ：" + data.equals(decrypt));
    }

}