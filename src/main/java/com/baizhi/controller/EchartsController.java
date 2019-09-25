package com.baizhi.controller;

import com.baizhi.entity.Pro;
import com.baizhi.service.EchartsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("/echarts")
public class EchartsController {
    @Resource
    private EchartsService echartsService;

    // 查询Echarts集合，返回月份和人数
    @RequestMapping("/selectBySexAndMonth")
    public Map<String,Object> selectBySexAndMonth(){
        // 调用service层方法
        Map<String,Object> map = echartsService.selectBySexAndMonth();

        System.out.println(map);
        return map;
    }

    @RequestMapping("/queryUserMap")
    public ArrayList<Pro> queryUserMap(){
        ArrayList<Pro>  pros =echartsService.queryUserMap();
        return pros;
    }
}
