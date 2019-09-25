package com.baizhi.service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public interface AdminService {

    // 管理员登录方法
    HashMap<String, Object> Adminlogin(String enCode,String username,String password,HttpServletRequest request);

}
