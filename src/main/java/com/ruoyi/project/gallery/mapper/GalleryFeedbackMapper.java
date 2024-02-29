package com.ruoyi.project.gallery.mapper;

import java.util.List;
import com.ruoyi.project.gallery.domain.GalleryFeedback;

/**
 * 反馈管理Mapper接口
 * 
 * @author ogcfun
 * @date 2024-02-29
 */
public interface GalleryFeedbackMapper 
{
    /**
     * 查询反馈管理
     * 
     * @param fbId 反馈管理主键
     * @return 反馈管理
     */
    public GalleryFeedback selectGalleryFeedbackByFbId(Long fbId);

    /**
     * 查询反馈管理列表
     * 
     * @param galleryFeedback 反馈管理
     * @return 反馈管理集合
     */
    public List<GalleryFeedback> selectGalleryFeedbackList(GalleryFeedback galleryFeedback);

    /**
     * 新增反馈管理
     * 
     * @param galleryFeedback 反馈管理
     * @return 结果
     */
    public int insertGalleryFeedback(GalleryFeedback galleryFeedback);

    /**
     * 修改反馈管理
     * 
     * @param galleryFeedback 反馈管理
     * @return 结果
     */
    public int updateGalleryFeedback(GalleryFeedback galleryFeedback);

    /**
     * 删除反馈管理
     * 
     * @param fbId 反馈管理主键
     * @return 结果
     */
    public int deleteGalleryFeedbackByFbId(Long fbId);

    /**
     * 批量删除反馈管理
     * 
     * @param fbIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGalleryFeedbackByFbIds(Long[] fbIds);
}
