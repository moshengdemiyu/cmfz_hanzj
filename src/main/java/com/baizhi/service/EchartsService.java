package com.baizhi.service;

import com.baizhi.entity.Pro;

import java.util.ArrayList;
import java.util.Map;

public interface EchartsService {

    // 通过sex和month，查询月份和人数
    Map<String,Object> selectBySexAndMonth();

    // 查询用户分步返回ArrayList集合
    ArrayList<Pro> queryUserMap();

}
