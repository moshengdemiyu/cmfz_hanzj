package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Excel(name="ID")
    private String id;
    @Excel(name="照片",type = 2,width = 18,height = 30)
    private String photo;
    @Excel(name="姓名")
    private String name;
    @Excel(name="法名")
    private String nickName;
    @Excel(name="性别")
    private String sex;
    @Excel(name = "城市")
    private String city;
    @ExcelIgnore
    private String password;
    @ExcelIgnore
    private String salt;
    @Excel(name = "状态")
    private String status;
    @Excel(name = "签名")
    private String sign;
    @Excel(name = "电话")
    private String phone;
    @Excel(name = "发布时间",format = "yyyy-MM-dd",width = 20,height = 20)
    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date crea_date;
    @Excel(name = "上师ID")
    private String gurud_id;

}
