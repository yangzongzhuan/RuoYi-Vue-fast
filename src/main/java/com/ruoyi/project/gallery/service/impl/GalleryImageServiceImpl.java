package com.ruoyi.project.gallery.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.gallery.mapper.GalleryImageMapper;
import com.ruoyi.project.gallery.domain.GalleryImage;
import com.ruoyi.project.gallery.service.IGalleryImageService;

/**
 * 列表管理Service业务层处理
 * 
 * @author ogcfun
 * @date 2024-02-28
 */
@Service
public class GalleryImageServiceImpl implements IGalleryImageService 
{
    @Autowired
    private GalleryImageMapper galleryImageMapper;

    /**
     * 查询列表管理
     * 
     * @param fileId 列表管理主键
     * @return 列表管理
     */
    @Override
    public GalleryImage selectGalleryImageByFileId(Long fileId)
    {
        return galleryImageMapper.selectGalleryImageByFileId(fileId);
    }

    /**
     * 查询列表管理列表
     * 
     * @param galleryImage 列表管理
     * @return 列表管理
     */
    @Override
    public List<GalleryImage> selectGalleryImageList(GalleryImage galleryImage)
    {
        return galleryImageMapper.selectGalleryImageList(galleryImage);
    }

    /**
     * 新增列表管理
     * 
     * @param galleryImage 列表管理
     * @return 结果
     */
    @Override
    public int insertGalleryImage(GalleryImage galleryImage)
    {
        return galleryImageMapper.insertGalleryImage(galleryImage);
    }

    /**
     * 修改列表管理
     * 
     * @param galleryImage 列表管理
     * @return 结果
     */
    @Override
    public int updateGalleryImage(GalleryImage galleryImage)
    {
        return galleryImageMapper.updateGalleryImage(galleryImage);
    }

    /**
     * 批量删除列表管理
     * 
     * @param fileIds 需要删除的列表管理主键
     * @return 结果
     */
    @Override
    public int deleteGalleryImageByFileIds(Long[] fileIds)
    {
        return galleryImageMapper.deleteGalleryImageByFileIds(fileIds);
    }

    /**
     * 删除列表管理信息
     * 
     * @param fileId 列表管理主键
     * @return 结果
     */
    @Override
    public int deleteGalleryImageByFileId(Long fileId)
    {
        return galleryImageMapper.deleteGalleryImageByFileId(fileId);
    }
}
