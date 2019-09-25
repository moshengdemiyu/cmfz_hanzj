package com.baizhi.dao;

import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDAO {

    // 展示所有article方法
    List<User> showAll(@Param("page")Integer page, @Param("rows") Integer rows);
    Integer selectCount();

    // 添加方法
    void addUser(User user);

    // 修改方法
    void editUser(User user);

    // 删除方法
    void delUser(String id);

    User selectById(String rowId);

    // 不分页查询所有
    List<User> searchAll();

}
