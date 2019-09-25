package com.baizhi.dao;

import com.baizhi.entity.Album;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlbumDAO {

    // 分页，查询所有
    List<Album> showAll(@Param("page")Integer page,@Param("rows")Integer rows);
    // 查询总条数
    Integer selectCount();

    // 添加操作
    void addAlbum(Album album);

    // 修改操作
    void editAlbum(Album album);

    // 删除操作
    void delAlbum(String id);
}
