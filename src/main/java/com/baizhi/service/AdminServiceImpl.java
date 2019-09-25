package com.baizhi.service;

import com.baizhi.dao.AdminDAO;
import com.baizhi.entity.Admin;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminDAO adminDAO;

    @Override
    public HashMap<String, Object> Adminlogin(String enCode,String username,String password,HttpServletRequest request) {
        String imageCode = (String) request.getSession().getAttribute("imageCode");

        HashMap<String,Object> map = new HashMap<>();

        // 调用dao层方法
        Admin admin = adminDAO.selectAdmin(username,password);

        // 判断验证码  如果输入的验证码和原始验证码相同 则判断用户名
        if(imageCode.equalsIgnoreCase(enCode)){
//            if(username.equals(admin.getUsername())){
//                if(password.equals(admin.getPassword())){
                if(admin!=null){
//                    Admin admin1 = new Admin("1","admin","111111");
                    request.getSession().setAttribute("admin",admin);

                    map.put("success","200");
                    map.put("message","登陆成功");
                }else{
                    map.put("success","400");
                    map.put("message","用户名或密码输入错误");
                }
            }
//            else{
//                map.put("success","400");
//                map.put("message","用户名不存在");
//            }
        else{
            map.put("success","400");
            map.put("message","验证码输入错误");
        }
        return map;
    }
}
