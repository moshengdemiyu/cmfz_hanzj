package com.baizhi.controller;

import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/chapter")
public class ChapterController {
    @Resource
    private ChapterService chapterService;

    @RequestMapping("/showAll")
    public Map<String,Object> showAll(String albumId,Integer page, Integer rows){
        HashMap<String, Object> map = chapterService.selectAll(albumId, page, rows);
        return map;
    }

    @RequestMapping("/edit")
    public String edit(Chapter chapter,String albumId, String oper){

        String id=null;

        if(oper.equals("add")){
            // 添加操作
            chapter.setAlbum_id(albumId);
            id = chapterService.add(chapter);
        }
        if(oper.equals("edit")){
            // 修改操作
            chapterService.edit(chapter);
        }
        if(oper.equals("del")){
            // 删除操作
            chapterService.del(chapter.getId());
        }
        return id;
    }

    // 上传操作
    @RequestMapping("/chapterUpload")
    public HashMap<String, Object> chapterUpload(MultipartFile url, String id, HttpServletRequest request){
        HashMap<String, Object> map = chapterService.uploadChapter(url, id, request);

        return map;
    }

    // 下载操作
    @RequestMapping("/chapterdownload")
    public void chapterdownload(String fileName, HttpServletRequest request, HttpServletResponse response){
        chapterService.downloadChapter(fileName, request, response);
    }

}
