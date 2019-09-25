package com.baizhi.service;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.dao.UserDAO;
import com.baizhi.entity.User;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Resource
    private UserDAO userDAO;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public HashMap<String, Object> showAll(Integer page, Integer rows) {
        List<User> users = userDAO.showAll(page, rows);
        HashMap<String, Object> map = new HashMap<>();
        map.put("rows",users);
        Integer count = userDAO.selectCount();
        map.put("records",count);
        Integer total= count%rows==0?count/rows:count/rows+1;
        map.put("total",total);
        map.put("page",page);
        return map;
    }

    @Override
    public String add(User user) {
        return null;
    }

    @Override
    public void edit(User user) {

    }

    @Override
    public void del(String id) {

    }

    //修改用户状态
    @Override
    public void changeStatus(String rowId) {
        User user=userDAO.selectById(rowId);
        if(user.getStatus().equals("冻结")){
            user.setStatus("可使用");
        }else {
            user.setStatus("冻结");
        }
        userDAO.editUser(user);
    }

    // 查询所有
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Map<String, Object> exportUser(HttpServletRequest request){
        // 获取真实路径
        String realPath = request.getSession().getServletContext().getRealPath("/upload/photo");
        HashMap<String, Object> map = new HashMap<>();
        // 调用dao层方法查询出对象集合
        List<User> list = userDAO.searchAll();
        // 创建一个ArrayList集合
        ArrayList<User> users = new ArrayList<>();
        for (User user : list) {
            //
            String photo = user.getPhoto();
            System.out.println(photo);
            user.setPhoto(realPath+"//"+user.getPhoto());
            users.add(user);
        }
        try{
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户信息列表", "用户表"), User.class, users);
            workbook.write(new FileOutputStream(new File("E:/导出用户信息.xls")));
            map.put("success",200);
            map.put("message","导出成功");
            workbook.close();
        }catch (Exception e){
            e.printStackTrace();
            map.put("success",400);
            map.put("message","导出失败");
        }

        return map;
    }

}
