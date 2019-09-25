package com.baizhi.dao;

import com.baizhi.entity.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminDAO {

    // 查询管理员方法
    Admin selectAdmin(@Param("username") String username,@Param("password") String password);

}
