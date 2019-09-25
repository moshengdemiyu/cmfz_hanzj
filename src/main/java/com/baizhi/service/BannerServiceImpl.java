package com.baizhi.service;

import com.baizhi.dao.BannerDAO;
import com.baizhi.entity.Banner;
import com.baizhi.util.UUIDUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {
    @Resource
    private BannerDAO bannerDAO;

    @Override
    public String add(Banner banner) {
        String uuid = UUIDUtil.getUUID();
        banner.setId(uuid);
        banner.setStatus("1");
        banner.setUp_date(new Date());
        bannerDAO.addBanner(banner);
        return uuid;
    }

    // 修改方法
    @Override
    public void edit(Banner banner) {
        bannerDAO.editBanner(banner);
    }

    // 删除方法
    @Override
    public void del(String id) {
        bannerDAO.delBanner(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public HashMap<String,Object> showAll(Integer page, Integer rows) {
        HashMap<String,Object> map = new HashMap<>();
        List<Banner> banners = bannerDAO.showAll(page,rows);
        Integer count = bannerDAO.selectCount();
        // 当前页展示的条数
        map.put("rows",banners);
        // 总条数
        map.put("records",count);
        int total = count%rows==0?count/rows:count/rows+1;
        // 总页数
        map.put("total",total);
        // 当前页
        map.put("page",page);
        return map;
    }

    @Override
    public HashMap<String, Object> updateStatus(Banner banner) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            bannerDAO.editBanner(banner);
            map.put("success","200");
            map.put("message","修改成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("success","400");
            map.put("message","修改失败");
        }
        return map;
    }
}
