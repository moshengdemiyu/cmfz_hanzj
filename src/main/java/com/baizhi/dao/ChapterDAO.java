package com.baizhi.dao;

import com.baizhi.entity.Chapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChapterDAO {

    // 展示所有操作
    List<Chapter> showAll(@Param("albumId")String albumId,@Param("page")Integer page,@Param("rows")Integer rows);
    // 查询条数
    Integer selectCount();

    // 添加操作
    void addChapter(Chapter chapter);

    // 修改操作
    void editChapter(Chapter chapter);

    // 删除操作
    void delChapter(String id);

}
