package com.ruoyi.project.gallery.service;

import java.util.List;

import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.gallery.domain.GalleryImage;
import org.springframework.web.multipart.MultipartFile;

/**
 * 列表管理Service接口
 * 
 * @author ogcfun
 * @date 2024-02-28
 */
public interface IGalleryImageService 
{
    /**
     * 查询列表管理
     * 
     * @param fileId 列表管理主键
     * @return 列表管理
     */
    public GalleryImage selectGalleryImageByFileId(Long fileId);

    /**
     * 查询列表管理列表
     * 
     * @param galleryImage 列表管理
     * @return 列表管理集合
     */
    public List<GalleryImage> selectGalleryImageList(GalleryImage galleryImage);

    /**
     * 新增列表管理
     * 
     * @param galleryImage 列表管理
     * @return 结果
     */
    public int insertGalleryImage(GalleryImage galleryImage);

    /**
     * 修改列表管理
     * 
     * @param galleryImage 列表管理
     * @return 结果
     */
    public int updateGalleryImage(GalleryImage galleryImage);

    /**
     * 批量删除列表管理
     * 
     * @param fileIds 需要删除的列表管理主键集合
     * @return 结果
     */
    public int deleteGalleryImageByFileIds(Long[] fileIds);

    /**
     * 删除列表管理信息
     * 
     * @param fileId 列表管理主键
     * @return 结果
     */
    public int deleteGalleryImageByFileId(Long fileId);

    /**
     * 上传/修改单张列表图片
     * @param fileType 图片文件夹路径
     * @param file 图片文件
     * @return 图片数据信息
     */
    public AjaxResult uploadGalleryImageByImage(String fileType ,String fileOrigin, MultipartFile file);

}
