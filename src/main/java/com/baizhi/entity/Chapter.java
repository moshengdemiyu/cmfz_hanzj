package com.baizhi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chapter {
    private String id;
    // 名字
    private String name;
    // 路径
    private String url;
    // 大小
    private String size;
    // 时长
    private String duration;
    // 发布时间
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date up_date;
    // 专辑ID
    private String album_id;
}
