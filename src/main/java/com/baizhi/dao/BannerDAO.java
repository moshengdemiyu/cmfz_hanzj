package com.baizhi.dao;

import com.baizhi.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BannerDAO {

    // 展示所有banner方法
    List<Banner> showAll(@Param("page")Integer page,@Param("rows") Integer rows);
    Integer selectCount();

    // 添加方法
    void addBanner(Banner banner);

    // 修改方法
    void editBanner(Banner banner);

    // 删除方法
    void delBanner(String id);
}
