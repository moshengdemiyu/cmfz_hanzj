package com.baizhi.service;

import com.baizhi.dao.ChapterDAO;
import com.baizhi.entity.Chapter;
import com.baizhi.util.UUIDUtil;
import org.apache.commons.io.IOUtils;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {
    @Resource
    private ChapterDAO chapterDAO;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public HashMap<String,Object> selectAll(String albumId, Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();

        List<Chapter> chapters = chapterDAO.showAll(albumId, page, rows);
        Integer count = chapterDAO.selectCount();

        map.put("rows",chapters);
        map.put("records",count);
        int total = count % rows == 0 ? count / rows : count / rows + 1;
        map.put("total",total);
        map.put("page",page);

        return map;
    }
    // 添加操作
    @Override
    public String add(Chapter chapter) {
        String uuid = UUIDUtil.getUUID();
        chapter.setId(uuid);
        chapter.setUp_date(new Date());
        System.out.println("=调用Dao方法执行添加   数据入库= "+chapter);
        chapterDAO.addChapter(chapter);
        return uuid;
    }

    @Override
    public void edit(Chapter chapter) {
        chapterDAO.editChapter(chapter);
    }

    @Override
    public void del(String id) {
        chapterDAO.delChapter(id);
    }

    @Override
    public HashMap<String, Object> uploadChapter(MultipartFile url, String id, HttpServletRequest request) {
        HashMap<String, Object> map = new HashMap<>();
        try{
            // 获取绝对路径
            String realPath = request.getSession().getServletContext().getRealPath("/upload/audio");
            File file = new File(realPath);
            if(!file.exists()){
                file.mkdirs();
            }
            // 获取文件名
            String filename = url.getOriginalFilename();
            String name = new Date().getTime()+"-"+filename;
            // 文件上传
            url.transferTo(new File(realPath,name));
            // 文件的大小 字节 Byte b KB MB
            double size = (double)url.getSize();
            double dd = size/1024/1024;
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            String sizes = decimalFormat.format(dd)+"MB";

            //获取文件时长
            AudioFile audioFile = AudioFileIO.read(new File(realPath, name));

            //获取时长 秒
            int length = audioFile.getAudioHeader().getTrackLength();

            String duration=length/60+"分"+length%60+"秒";

            //修改数据
            Chapter chapter = new Chapter();
            chapter.setId(id);
            chapter.setUrl(name);
            chapter.setSize(sizes);
            chapter.setDuration(duration);

            System.out.println("==调用Dao执行修改操作=="+chapter);
            chapterDAO.editChapter(chapter);

            map.put("success","200");
            map.put("message","上传成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success","400");
            map.put("message","上传失败");
        }
        return map;
    }

    @Override
    public void downloadChapter(String fileName, HttpServletRequest request, HttpServletResponse response) {
        try {
            // 1.获取文件的路径
            String realPath = request.getSession().getServletContext().getRealPath("/upload/audio");
            // 2.根据路径获取文件
            FileInputStream inputStream = new FileInputStream(new File(realPath, fileName));
            // 3.设置响应格式     响应头,文件名     attachment:以附件形式下载  inline:在线打开
            response.setHeader("content-disposition","attachment;fileName="+ URLEncoder.encode(fileName,"UTF-8"));

            //获取输出流
            ServletOutputStream outputStream = response.getOutputStream();

            //4.文件下载
            IOUtils.copy(inputStream,outputStream);

            //关闭资源
            //inputStream.close();
            //outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}