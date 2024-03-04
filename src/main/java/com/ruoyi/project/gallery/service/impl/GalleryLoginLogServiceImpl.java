package com.ruoyi.project.gallery.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.gallery.mapper.GalleryLoginLogMapper;
import com.ruoyi.project.gallery.domain.GalleryLoginLog;
import com.ruoyi.project.gallery.service.IGalleryLoginLogService;

/**
 * 登录日志Service业务层处理
 * 
 * @author ogcfun
 * @date 2024-03-04
 */
@Service
public class GalleryLoginLogServiceImpl implements IGalleryLoginLogService 
{
    @Autowired
    private GalleryLoginLogMapper galleryLoginLogMapper;

    /**
     * 查询登录日志
     * 
     * @param logId 登录日志主键
     * @return 登录日志
     */
    @Override
    public GalleryLoginLog selectGalleryLoginLogByLogId(Long logId)
    {
        return galleryLoginLogMapper.selectGalleryLoginLogByLogId(logId);
    }

    /**
     * 查询登录日志列表
     * 
     * @param galleryLoginLog 登录日志
     * @return 登录日志
     */
    @Override
    public List<GalleryLoginLog> selectGalleryLoginLogList(GalleryLoginLog galleryLoginLog)
    {
        return galleryLoginLogMapper.selectGalleryLoginLogList(galleryLoginLog);
    }

    /**
     * 新增登录日志
     * 
     * @param galleryLoginLog 登录日志
     * @return 结果
     */
    @Override
    public int insertGalleryLoginLog(GalleryLoginLog galleryLoginLog)
    {
        return galleryLoginLogMapper.insertGalleryLoginLog(galleryLoginLog);
    }

    /**
     * 修改登录日志
     * 
     * @param galleryLoginLog 登录日志
     * @return 结果
     */
    @Override
    public int updateGalleryLoginLog(GalleryLoginLog galleryLoginLog)
    {
        return galleryLoginLogMapper.updateGalleryLoginLog(galleryLoginLog);
    }

    /**
     * 批量删除登录日志
     * 
     * @param logIds 需要删除的登录日志主键
     * @return 结果
     */
    @Override
    public int deleteGalleryLoginLogByLogIds(Long[] logIds)
    {
        return galleryLoginLogMapper.deleteGalleryLoginLogByLogIds(logIds);
    }

    /**
     * 删除登录日志信息
     * 
     * @param logId 登录日志主键
     * @return 结果
     */
    @Override
    public int deleteGalleryLoginLogByLogId(Long logId)
    {
        return galleryLoginLogMapper.deleteGalleryLoginLogByLogId(logId);
    }
}
