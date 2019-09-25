package com.baizhi.service;

import com.baizhi.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public interface UserService {
    // 查询操作
    HashMap<String,Object> showAll(Integer page, Integer rows);

    // 添加操作
    String add(User user);

    // 修改操作
    void edit(User user);

    // 删除操作
    void del(String id);

    // 修改用户状态
    void changeStatus(String rowId);

    // 导出用户
    Map<String, Object> exportUser(HttpServletRequest request);

}
