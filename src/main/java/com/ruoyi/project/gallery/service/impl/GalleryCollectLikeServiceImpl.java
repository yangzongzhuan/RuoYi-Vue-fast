package com.ruoyi.project.gallery.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.gallery.mapper.GalleryCollectLikeMapper;
import com.ruoyi.project.gallery.domain.GalleryCollectLike;
import com.ruoyi.project.gallery.service.IGalleryCollectLikeService;

/**
 * 收藏管理Service业务层处理
 * 
 * @author ogcfun
 * @date 2024-02-29
 */
@Service
public class GalleryCollectLikeServiceImpl implements IGalleryCollectLikeService 
{
    @Autowired
    private GalleryCollectLikeMapper galleryCollectLikeMapper;

    /**
     * 查询收藏管理
     * 
     * @param id 收藏管理主键
     * @return 收藏管理
     */
    @Override
    public GalleryCollectLike selectGalleryCollectLikeById(Long id)
    {
        return galleryCollectLikeMapper.selectGalleryCollectLikeById(id);
    }

    /**
     * 查询收藏管理列表
     * 
     * @param galleryCollectLike 收藏管理
     * @return 收藏管理
     */
    @Override
    public List<GalleryCollectLike> selectGalleryCollectLikeList(GalleryCollectLike galleryCollectLike)
    {
        return galleryCollectLikeMapper.selectGalleryCollectLikeList(galleryCollectLike);
    }

    /**
     * 新增收藏管理
     * 
     * @param galleryCollectLike 收藏管理
     * @return 结果
     */
    @Override
    public int insertGalleryCollectLike(GalleryCollectLike galleryCollectLike)
    {
        return galleryCollectLikeMapper.insertGalleryCollectLike(galleryCollectLike);
    }

    /**
     * 修改收藏管理
     * 
     * @param galleryCollectLike 收藏管理
     * @return 结果
     */
    @Override
    public int updateGalleryCollectLike(GalleryCollectLike galleryCollectLike)
    {
        return galleryCollectLikeMapper.updateGalleryCollectLike(galleryCollectLike);
    }

    /**
     * 批量删除收藏管理
     * 
     * @param ids 需要删除的收藏管理主键
     * @return 结果
     */
    @Override
    public int deleteGalleryCollectLikeByIds(Long[] ids)
    {
        return galleryCollectLikeMapper.deleteGalleryCollectLikeByIds(ids);
    }

    /**
     * 删除收藏管理信息
     * 
     * @param id 收藏管理主键
     * @return 结果
     */
    @Override
    public int deleteGalleryCollectLikeById(Long id)
    {
        return galleryCollectLikeMapper.deleteGalleryCollectLikeById(id);
    }
}
