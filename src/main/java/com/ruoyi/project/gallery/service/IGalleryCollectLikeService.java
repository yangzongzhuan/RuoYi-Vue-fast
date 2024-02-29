package com.ruoyi.project.gallery.service;

import java.util.List;
import com.ruoyi.project.gallery.domain.GalleryCollectLike;

/**
 * 收藏管理Service接口
 * 
 * @author ogcfun
 * @date 2024-02-29
 */
public interface IGalleryCollectLikeService 
{
    /**
     * 查询收藏管理
     * 
     * @param id 收藏管理主键
     * @return 收藏管理
     */
    public GalleryCollectLike selectGalleryCollectLikeById(Long id);

    /**
     * 查询收藏管理列表
     * 
     * @param galleryCollectLike 收藏管理
     * @return 收藏管理集合
     */
    public List<GalleryCollectLike> selectGalleryCollectLikeList(GalleryCollectLike galleryCollectLike);

    /**
     * 新增收藏管理
     * 
     * @param galleryCollectLike 收藏管理
     * @return 结果
     */
    public int insertGalleryCollectLike(GalleryCollectLike galleryCollectLike);

    /**
     * 修改收藏管理
     * 
     * @param galleryCollectLike 收藏管理
     * @return 结果
     */
    public int updateGalleryCollectLike(GalleryCollectLike galleryCollectLike);

    /**
     * 批量删除收藏管理
     * 
     * @param ids 需要删除的收藏管理主键集合
     * @return 结果
     */
    public int deleteGalleryCollectLikeByIds(Long[] ids);

    /**
     * 删除收藏管理信息
     * 
     * @param id 收藏管理主键
     * @return 结果
     */
    public int deleteGalleryCollectLikeById(Long id);
}
