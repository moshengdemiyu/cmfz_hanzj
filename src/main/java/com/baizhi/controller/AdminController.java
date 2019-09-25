package com.baizhi.controller;

import com.baizhi.service.AdminService;
import com.baizhi.util.ImageCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/getImageCode")
    public void getImageCode(HttpServletRequest request, HttpServletResponse response) {

        // 1.获取验证码随机字符
        String code = ImageCodeUtil.getSecurityCode();
        // 2.存储验证码字符
        request.getSession().setAttribute("imageCode", code);
        // 3.将验证码字符生成图片
        BufferedImage image = ImageCodeUtil.createImage(code);
        // 4.设置相应格式
        response.setContentType("image/png");
        // 5.将图片响应到页面
        try {
            ImageIO.write(image, "png", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ResponseBody
    @RequestMapping("/Adminlogin")
    public HashMap<String,Object> Adminlogin(String enCode,String username,String password,HttpServletRequest request){
        HashMap<String, Object> map = adminService.Adminlogin(enCode, username, password, request);
        return map;
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("admin");
        return "redirect:/login/login.jsp";
    }

}
