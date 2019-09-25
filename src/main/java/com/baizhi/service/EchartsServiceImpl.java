package com.baizhi.service;

import com.baizhi.dao.EchartsDAO;
import com.baizhi.entity.City;
import com.baizhi.entity.Pro;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class EchartsServiceImpl implements EchartsService {
    @Resource
    private EchartsDAO echartsDAO;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Map<String, Object> selectBySexAndMonth() {
        HashMap<String, Object> map = new HashMap<>();

        List<String> months = echartsDAO.selectMonth();
        ArrayList<Integer> boys = new ArrayList<>();
        ArrayList<Integer> girls = new ArrayList<>();
        for (String month : months) {
            int i = echartsDAO.selectBySexAndMon("男", month);
            boys.add(i);
        }
        for (String month : months) {
            int i = echartsDAO.selectBySexAndMon("女", month);
            girls.add(i);
        }

        map.put("month",months);
        map.put("boys",boys);
        map.put("girls",girls);

        return map;
    }

    @Override
    public ArrayList<Pro> queryUserMap() {
        ArrayList<Pro> pros = new ArrayList<>();

        ArrayList<City> boys = new ArrayList<>();
        List<City> citysM=echartsDAO.queryCityBySex("男");
        for (City city : citysM) {
            boys.add(city);
        }

        ArrayList<City> girls = new ArrayList<>();
        List<City> citysF=echartsDAO.queryCityBySex("女");
        for (City city : citysF) {
            girls.add(city);
        }

        Pro pro1 = new Pro("小男生", boys);
        Pro pro2 = new Pro("小姑娘", girls);

        pros.add(pro1);
        pros.add(pro2);

        return pros;
    }
}
