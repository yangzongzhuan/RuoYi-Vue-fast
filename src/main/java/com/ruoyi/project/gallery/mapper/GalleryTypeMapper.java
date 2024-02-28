package com.ruoyi.project.gallery.mapper;

import java.util.List;
import com.ruoyi.project.gallery.domain.GalleryType;

/**
 * 分类管理Mapper接口
 * 
 * @author ogcfun
 * @date 2024-02-28
 */
public interface GalleryTypeMapper 
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
     * 删除分类管理
     * 
     * @param typeId 分类管理主键
     * @return 结果
     */
    public int deleteGalleryTypeByTypeId(Long typeId);

    /**
     * 批量删除分类管理
     * 
     * @param typeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGalleryTypeByTypeIds(Long[] typeIds);
}
