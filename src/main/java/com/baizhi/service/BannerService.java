package com.baizhi.service;

import com.baizhi.entity.Banner;

import java.util.HashMap;

public interface BannerService {

    // 添加方法
    String add(Banner banner);

    // 修改方法
    void edit(Banner banner);

    // 删除方法
    void del(String id);

    // 展示所有方法
    HashMap<String,Object> showAll(Integer page, Integer rows);

    // 展示状态
    HashMap<String,Object> updateStatus(Banner banner);
}
