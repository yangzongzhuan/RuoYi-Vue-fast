package com.ruoyi.project.gallery.mapper;

import java.util.List;
import com.ruoyi.project.gallery.domain.GalleryLoginLog;

/**
 * 登录日志Mapper接口
 * 
 * @author ogcfun
 * @date 2024-03-04
 */
public interface GalleryLoginLogMapper 
{
    /**
     * 查询登录日志
     * 
     * @param logId 登录日志主键
     * @return 登录日志
     */
    public GalleryLoginLog selectGalleryLoginLogByLogId(Long logId);

    /**
     * 查询登录日志列表
     * 
     * @param galleryLoginLog 登录日志
     * @return 登录日志集合
     */
    public List<GalleryLoginLog> selectGalleryLoginLogList(GalleryLoginLog galleryLoginLog);

    /**
     * 新增登录日志
     * 
     * @param galleryLoginLog 登录日志
     * @return 结果
     */
    public int insertGalleryLoginLog(GalleryLoginLog galleryLoginLog);

    /**
     * 修改登录日志
     * 
     * @param galleryLoginLog 登录日志
     * @return 结果
     */
    public int updateGalleryLoginLog(GalleryLoginLog galleryLoginLog);

    /**
     * 删除登录日志
     * 
     * @param logId 登录日志主键
     * @return 结果
     */
    public int deleteGalleryLoginLogByLogId(Long logId);

    /**
     * 批量删除登录日志
     * 
     * @param logIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGalleryLoginLogByLogIds(Long[] logIds);
}
