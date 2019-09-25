package com.baizhi.service;

import com.baizhi.entity.Article;

import java.util.HashMap;

public interface ArticleService {

    HashMap<String,Object> showAll(Integer page,Integer rows);

    void del(String id);
    //异步修改
    HashMap<String, Object> edit(Article article);
    //添加文章
    HashMap<String, Object> add(Article article);
}