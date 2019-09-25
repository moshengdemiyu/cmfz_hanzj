package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pro {
    //女生 or 男生
    private String title;
    //对应的城市集合（“城市名”，注册量）
    private ArrayList<City> citys;

}
