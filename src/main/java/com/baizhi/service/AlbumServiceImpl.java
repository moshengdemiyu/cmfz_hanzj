package com.baizhi.service;

import com.baizhi.dao.AlbumDAO;
import com.baizhi.entity.Album;
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
public class AlbumServiceImpl implements AlbumService {
    @Resource
    private AlbumDAO albumDAO;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public HashMap<String, Object> showAll(Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();
        // 查询集合进行分页                 // 当前页  // 每页展示条数
        List<Album> albums = albumDAO.showAll(page, rows);
        // 查询总条数
        Integer count = albumDAO.selectCount();
        // 当前展示条数
        map.put("rows", albums);
        // 总条数
        map.put("records", count);
        int total = count % rows == 0 ? count / rows : count / rows + 1;
        // 总页数
        map.put("total", total);
        // 当前页
        map.put("page", page);
        return map;
    }
    // 添加操作
    @Override
    public String add(Album album) {
        String uuid = UUIDUtil.getUUID();
        album.setId(uuid);
        album.setCrea_date(new Date());
        albumDAO.addAlbum(album);
        return uuid;
    }
    // 修改操作
    @Override
    public void edit(Album album) {
        albumDAO.editAlbum(album);
    }

    // 删除操作
    @Override
    public void del(String id) {
        albumDAO.delAlbum(id);
    }

}
