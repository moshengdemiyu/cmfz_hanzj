package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/Album")
public class AlbumController {
    @Resource
    private AlbumService albumService;

    @RequestMapping("/showAll")
    public Map<String,Object> showAll(Integer page,Integer rows){
        Map<String, Object> map = albumService.showAll(page, rows);
        System.out.println(map+"------路径是什么------");
        return map;
    }

    @RequestMapping("/edit")
    public String edit(Album album, String oper){
        String id = null;
        // 添加方法
        if(oper.equals("add")){
            id = albumService.add(album);
        }
        // 执行修改操作
        if(oper.equals("edit")){
            albumService.edit(album);
        }

        // 执行删除操作
        if(oper.equals("del")){
            albumService.del(album.getId());
        }
        return id;
    }

    @RequestMapping("/albumUpload")
    public void albumUpload(MultipartFile cover, String id, HttpServletRequest request){
        System.out.println("是不是没走这里");
        // 1.根据相对路径获取绝对路径
        String realPath = request.getSession().getServletContext().getRealPath("/upload/photo");

        // 获取文件夹
        File file = new File(realPath);
        // 判断文件夹是否存在
        if(!file.exists()){
            boolean mkdirs = file.mkdirs(); // 创建文件夹
        }
        // 获取文件名
        String filename = cover.getOriginalFilename();

        // 给文件加一个时间戳
        String name = new Date().getTime()+"-"+filename;

        // 文件上传
        try {
            cover.transferTo(new File(realPath,name));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 修改文件路径
        System.out.println("这个是什么？？？？进来");
        Album album = new Album();
        System.out.println(album+"这个是什么");
        album.setId(id);
        album.setCover(name);
        System.out.println("修改了吗？"+name);
        albumService.edit(album);
    }
}
