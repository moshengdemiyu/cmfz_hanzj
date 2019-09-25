package com.baizhi.controller;

import com.baizhi.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    //查询所有
    @RequestMapping("/showAll")
    public HashMap<String,Object> showAll(Integer page, Integer rows)throws Exception{
        HashMap<String, Object> map = userService.showAll(page, rows);
        return map;
    }

    //修改用户状态
    @RequestMapping("/changeStatus")
    public void changeStatus(String rowId)throws Exception{
        userService.changeStatus(rowId);
    }

    @RequestMapping("/export")
    public Map<String,Object> exportUser(HttpServletRequest request) {
        Map<String, Object> map = userService.exportUser(request);
        return map;
    }

}
