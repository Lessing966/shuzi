package com.example.demo.controller;

import com.example.demo.common.ExcelUtils;
import com.example.demo.common.R;
import com.example.demo.dmdao.DmDao;
import com.example.demo.dto.DataListDTO;
import com.example.demo.entity.DateEntity;
import com.example.demo.entity.PlatfromEntity;
import com.example.demo.service.WebService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/sys")
@Api(tags = "数据查询")
public class WebController {

    @Autowired
    WebService webService;


    /**
     * 注入 jdbcTemplate 模板对象
     */
    @Autowired
    DmDao dmDao;

    /**
     * 编写测试方法
     */
    @ApiOperation("version")
    @GetMapping("/version")
    @ResponseBody
    public String version(String name) {
        //查询数据库版本信息
//        Date date =new Date();
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String map = dmDao.queryForMap(name,format);
        //返回查询结果
        return map;
    }

    @ApiOperation("数据列表")
    @PostMapping("/list")
    public R list(@RequestBody DataListDTO dto){
        return R.ok().put("data",webService.selectList(dto));
    }


    @GetMapping("/outDataExcel")
    @ApiOperation("一键导出")
    public void outExcel(DataListDTO dto, HttpServletResponse response){
        List<DateEntity> DataListDTOS = webService.outExcel(dto);
        ExcelUtils.writeExcel(response,DataListDTOS, DateEntity.class,"数据信息.xlsx");
    }


    @PostMapping("/getPlatform")
    @ApiOperation("所有平台")
    public R getPlatform(@RequestBody PlatfromEntity entity){
        return R.ok().put("platform", webService.selectAllPlatform(entity));
    }


    @PostMapping("/AddPlatform")
    @ApiOperation("新增平台")
    public R AddPlatform(@RequestBody PlatfromEntity entity){
        webService.AddPlatform(entity);
        return R.ok();
    }

    @PostMapping("/UpdatePlatform")
    @ApiOperation("修改平台")
    public R UpdatePlatform(@RequestBody PlatfromEntity entity){
        webService.UpdatePlatform(entity);
        return R.ok();
    }

    @PostMapping("/DeletePlatform")
    @ApiOperation("删除平台")
    public R DeletePlatform(Integer id){
        webService.DeletePlatform(id);
        return R.ok();
    }

}