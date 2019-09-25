package com.baizhi.dao;

import com.baizhi.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleDAO {

    // 展示所有article方法
    List<Article> showAll(@Param("page")Integer page, @Param("rows") Integer rows);
    Integer selectCount();

    // 添加方法
    void addArticle(Article article);

    // 修改方法
    void editArticle(Article article);

    // 删除方法
    void delArticle(String id);
}
