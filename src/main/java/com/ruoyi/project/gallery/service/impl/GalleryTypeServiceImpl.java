package com.ruoyi.project.gallery.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.gallery.mapper.GalleryTypeMapper;
import com.ruoyi.project.gallery.domain.GalleryType;
import com.ruoyi.project.gallery.service.IGalleryTypeService;

/**
 * 分类管理Service业务层处理
 * 
 * @author ogcfun
 * @date 2024-02-28
 */
@Service
public class GalleryTypeServiceImpl implements IGalleryTypeService 
{
    @Autowired
    private GalleryTypeMapper galleryTypeMapper;

    /**
     * 查询分类管理
     * 
     * @param typeId 分类管理主键
     * @return 分类管理
     */
    @Override
    public GalleryType selectGalleryTypeByTypeId(Long typeId)
    {
        return galleryTypeMapper.selectGalleryTypeByTypeId(typeId);
    }

    /**
     * 查询分类管理列表
     * 
     * @param galleryType 分类管理
     * @return 分类管理
     */
    @Override
    public List<GalleryType> selectGalleryTypeList(GalleryType galleryType)
    {
        return galleryTypeMapper.selectGalleryTypeList(galleryType);
    }

    /**
     * 新增分类管理
     * 
     * @param galleryType 分类管理
     * @return 结果
     */
    @Override
    public int insertGalleryType(GalleryType galleryType)
    {
        return galleryTypeMapper.insertGalleryType(galleryType);
    }

    /**
     * 修改分类管理
     * 
     * @param galleryType 分类管理
     * @return 结果
     */
    @Override
    public int updateGalleryType(GalleryType galleryType)
    {
        return galleryTypeMapper.updateGalleryType(galleryType);
    }

    /**
     * 批量删除分类管理
     * 
     * @param typeIds 需要删除的分类管理主键
     * @return 结果
     */
    @Override
    public int deleteGalleryTypeByTypeIds(Long[] typeIds)
    {
        return galleryTypeMapper.deleteGalleryTypeByTypeIds(typeIds);
    }

    /**
     * 删除分类管理信息
     * 
     * @param typeId 分类管理主键
     * @return 结果
     */
    @Override
    public int deleteGalleryTypeByTypeId(Long typeId)
    {
        return galleryTypeMapper.deleteGalleryTypeByTypeId(typeId);
    }
}
