package com.baizhi.service;

import com.baizhi.entity.Album;

import java.util.HashMap;


public interface AlbumService {

    // 查询功能
    HashMap<String,Object> showAll(Integer page, Integer rows);

    // 添加功能
    String add(Album album);

    // 修改功能
    void edit(Album album);

    // 删除功能
    void del(String id);
}
