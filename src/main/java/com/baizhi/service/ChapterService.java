package com.baizhi.service;

import com.baizhi.entity.Chapter;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public interface ChapterService {

    // 查询操作
    HashMap<String,Object> selectAll(String albumId, Integer page, Integer rows);

    // 添加操作
    String add(Chapter chapter);

    // 修改操作
    void edit(Chapter chapter);

    // 删除操作
    void del(String id);

    // 上传操作
    HashMap<String, Object> uploadChapter(MultipartFile url, String id, HttpServletRequest request);

    // 下载操作
    void downloadChapter(String fileName, HttpServletRequest request, HttpServletResponse response);
}
