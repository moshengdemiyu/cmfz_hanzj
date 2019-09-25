package com.baizhi.service;

import com.baizhi.dao.ArticleDAO;
import com.baizhi.entity.Article;
import com.baizhi.util.UUIDUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class ArticleServiceImpl implements  ArticleService{
    @Resource
    private ArticleDAO articleDAO;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public HashMap<String, Object> showAll(Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();

        List<Article> articles = articleDAO.showAll(page, rows);

        Integer count = articleDAO.selectCount();

        map.put("rows",articles);
        map.put("records",count);
        int total = count % rows == 0 ? count / rows : count / rows + 1;
        map.put("total",total);
        map.put("page",page);

        return map;
    }

    @Override
    public HashMap<String, Object> add(Article article) {
        HashMap<String, Object> map=new HashMap<>();
        try {
            article.setId(UUIDUtil.getUUID());
            article.setCrea_date(new Date());
            article.setGuru_id("上师ID");
            articleDAO.addArticle(article);
            map.put("success",200);
            map.put("message","添加成功");
        }catch (Exception e){
            map.put("success",400);
            map.put("message","添加失败");
        }
        return map;
    }

    @Override
    public HashMap<String, Object> edit(Article article) {
        HashMap<String, Object> map=new HashMap<>();
        try {
            articleDAO.editArticle(article);
            map.put("success",200);
            map.put("message","修改成功");
        }catch (Exception e){
            map.put("success",400);
            map.put("message","修改失败");
        }
        return map;
    }

    @Override
    public void del(String id) {
        articleDAO.delArticle(id);
    }
}
