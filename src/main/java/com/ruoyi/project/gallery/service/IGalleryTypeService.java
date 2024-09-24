package com.ruoyi.project.gallery.service;

import java.util.List;
import com.ruoyi.project.gallery.domain.GalleryType;

/**
 * 分类管理Service接口
 * 
 * @author ogcfun
 * @date 2024-02-28
 */
public interface IGalleryTypeService 
{
    /**
     * 查询分类管理
     * 
     * @param typeId 分类管理主键
     * @return 分类管理
     */
    public GalleryType selectGalleryTypeByTypeId(Long typeId);

    /**
     * 查询分类管理列表
     * 
     * @param galleryType 分类管理
     * @return 分类管理集合
     */
    public List<GalleryType> selectGalleryTypeList(GalleryType galleryType);

    /**
     * 新增分类管理
     * 
     * @param galleryType 分类管理
     * @return 结果
     */
    public int insertGalleryType(GalleryType galleryType);

    /**
     * 修改分类管理
     * 
     * @param galleryType 分类管理
     * @return 结果
     */
    public int updateGalleryType(GalleryType galleryType);

    /**
     * 批量删除分类管理
     * 
     * @param typeIds 需要删除的分类管理主键集合
     * @return 结果
     */
    public int deleteGalleryTypeByTypeIds(Long[] typeIds);

    /**
     * 删除分类管理信息
     * 
     * @param typeId 分类管理主键
     * @return 结果
     */
    public int deleteGalleryTypeByTypeId(Long typeId);
}
