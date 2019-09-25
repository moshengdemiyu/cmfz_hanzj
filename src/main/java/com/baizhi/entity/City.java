package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class City {
    //键：城市名
    private String name;
    //值：对应的注册用户量
    private String value;
}
