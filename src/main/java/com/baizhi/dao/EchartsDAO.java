package com.baizhi.dao;

import com.baizhi.entity.City;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EchartsDAO {

    // 查询月份
    List<String> selectMonth();

    // 查询每个月人数
    Integer selectBySexAndMon(@Param("sex")String sex,@Param("month") String month);

    // sex传入性别，根据月份分组，返回月份对应人数
    Integer selectpreson(String sex);

    // sex传入性别返回城市对应的集合
    List<City> queryCityBySex(String sex);
}
