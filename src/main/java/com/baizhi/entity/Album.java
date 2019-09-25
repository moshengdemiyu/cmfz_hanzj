package com.baizhi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Album {
    private String id;
    // 标题
    private String title;
    // 封面
    private String cover;
    // 作者
    private String author;
    // 评分
    private Double score;
    // 播音
    private String broadcast;
    // 集数
    private Integer count;
    // 内容
    private String content;
    // 发布时间
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date crea_date;
}
