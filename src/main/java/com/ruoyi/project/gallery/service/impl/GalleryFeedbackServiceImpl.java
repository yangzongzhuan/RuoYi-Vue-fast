package com.ruoyi.project.gallery.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.gallery.mapper.GalleryFeedbackMapper;
import com.ruoyi.project.gallery.domain.GalleryFeedback;
import com.ruoyi.project.gallery.service.IGalleryFeedbackService;

/**
 * 反馈管理Service业务层处理
 * 
 * @author ogcfun
 * @date 2024-02-29
 */
@Service
public class GalleryFeedbackServiceImpl implements IGalleryFeedbackService 
{
    @Autowired
    private GalleryFeedbackMapper galleryFeedbackMapper;

    /**
     * 查询反馈管理
     * 
     * @param fbId 反馈管理主键
     * @return 反馈管理
     */
    @Override
    public GalleryFeedback selectGalleryFeedbackByFbId(Long fbId)
    {
        return galleryFeedbackMapper.selectGalleryFeedbackByFbId(fbId);
    }

    /**
     * 查询反馈管理列表
     * 
     * @param galleryFeedback 反馈管理
     * @return 反馈管理
     */
    @Override
    public List<GalleryFeedback> selectGalleryFeedbackList(GalleryFeedback galleryFeedback)
    {
        return galleryFeedbackMapper.selectGalleryFeedbackList(galleryFeedback);
    }

    /**
     * 新增反馈管理
     * 
     * @param galleryFeedback 反馈管理
     * @return 结果
     */
    @Override
    public int insertGalleryFeedback(GalleryFeedback galleryFeedback)
    {
        return galleryFeedbackMapper.insertGalleryFeedback(galleryFeedback);
    }

    /**
     * 修改反馈管理
     * 
     * @param galleryFeedback 反馈管理
     * @return 结果
     */
    @Override
    public int updateGalleryFeedback(GalleryFeedback galleryFeedback)
    {
        return galleryFeedbackMapper.updateGalleryFeedback(galleryFeedback);
    }

    /**
     * 批量删除反馈管理
     * 
     * @param fbIds 需要删除的反馈管理主键
     * @return 结果
     */
    @Override
    public int deleteGalleryFeedbackByFbIds(Long[] fbIds)
    {
        return galleryFeedbackMapper.deleteGalleryFeedbackByFbIds(fbIds);
    }

    /**
     * 删除反馈管理信息
     * 
     * @param fbId 反馈管理主键
     * @return 结果
     */
    @Override
    public int deleteGalleryFeedbackByFbId(Long fbId)
    {
        return galleryFeedbackMapper.deleteGalleryFeedbackByFbId(fbId);
    }
}
