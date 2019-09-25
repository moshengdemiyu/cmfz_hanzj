package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Resource
    private ArticleService articleService;

    //分页查询
    @RequestMapping("/showAll")
    public HashMap<String,Object> showAll(Integer page,Integer rows){
        HashMap<String,Object> map = articleService.showAll(page, rows);
        System.out.println(map);
        return map;
    }

    //文章的增删改操作
    @RequestMapping("/edit")
    public String edit(Album album, String oper)throws Exception{
        String id=null;
        if(oper.equals("add")){

        }
        if(oper.equals("edit")){

        }
        if(oper.equals("del")){
            articleService.del(album.getId());
        }
        return id;
    }


    //修改文章
    @RequestMapping("/updateArticle")
    public HashMap<String,Object> updateArticle(Article article){
        HashMap<String,Object> map= articleService.edit(article);
        return map;
    }

    //添加文章
    @RequestMapping("/addArticle")
    public HashMap<String,Object> addArticle(Article article){
        HashMap<String,Object> map= articleService.add(article);
        return map;
    }
}