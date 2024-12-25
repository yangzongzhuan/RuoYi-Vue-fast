package com.ruoyi.project.gallery.mapper;

import java.util.List;
import com.ruoyi.project.gallery.domain.GalleryTask;

/**
 * 任务管理Mapper接口
 * 
 * @author ogcfun
 * @date 2024-02-29
 */
public interface GalleryTaskMapper 
{
    /**
     * 查询任务管理
     * 
     * @param taskId 任务管理主键
     * @return 任务管理
     */
    public GalleryTask selectGalleryTaskByTaskId(Long taskId);

    /**
     * 查询任务管理列表
     * 
     * @param galleryTask 任务管理
     * @return 任务管理集合
     */
    public List<GalleryTask> selectGalleryTaskList(GalleryTask galleryTask);

    /**
     * 新增任务管理
     * 
     * @param galleryTask 任务管理
     * @return 结果
     */
    public int insertGalleryTask(GalleryTask galleryTask);

    /**
     * 修改任务管理
     * 
     * @param galleryTask 任务管理
     * @return 结果
     */
    public int updateGalleryTask(GalleryTask galleryTask);

    /**
     * 删除任务管理
     * 
     * @param taskId 任务管理主键
     * @return 结果
     */
    public int deleteGalleryTaskByTaskId(Long taskId);

    /**
     * 批量删除任务管理
     * 
     * @param taskIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGalleryTaskByTaskIds(Long[] taskIds);
}
