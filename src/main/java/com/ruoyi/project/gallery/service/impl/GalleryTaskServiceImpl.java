package com.ruoyi.project.gallery.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.gallery.mapper.GalleryTaskMapper;
import com.ruoyi.project.gallery.domain.GalleryTask;
import com.ruoyi.project.gallery.service.IGalleryTaskService;

/**
 * 任务管理Service业务层处理
 * 
 * @author ogcfun
 * @date 2024-02-29
 */
@Service
public class GalleryTaskServiceImpl implements IGalleryTaskService 
{
    @Autowired
    private GalleryTaskMapper galleryTaskMapper;

    /**
     * 查询任务管理
     * 
     * @param taskId 任务管理主键
     * @return 任务管理
     */
    @Override
    public GalleryTask selectGalleryTaskByTaskId(Long taskId)
    {
        return galleryTaskMapper.selectGalleryTaskByTaskId(taskId);
    }

    /**
     * 查询任务管理列表
     * 
     * @param galleryTask 任务管理
     * @return 任务管理
     */
    @Override
    public List<GalleryTask> selectGalleryTaskList(GalleryTask galleryTask)
    {
        return galleryTaskMapper.selectGalleryTaskList(galleryTask);
    }

    /**
     * 新增任务管理
     * 
     * @param galleryTask 任务管理
     * @return 结果
     */
    @Override
    public int insertGalleryTask(GalleryTask galleryTask)
    {
        return galleryTaskMapper.insertGalleryTask(galleryTask);
    }

    /**
     * 修改任务管理
     * 
     * @param galleryTask 任务管理
     * @return 结果
     */
    @Override
    public int updateGalleryTask(GalleryTask galleryTask)
    {
        return galleryTaskMapper.updateGalleryTask(galleryTask);
    }

    /**
     * 批量删除任务管理
     * 
     * @param taskIds 需要删除的任务管理主键
     * @return 结果
     */
    @Override
    public int deleteGalleryTaskByTaskIds(Long[] taskIds)
    {
        return galleryTaskMapper.deleteGalleryTaskByTaskIds(taskIds);
    }

    /**
     * 删除任务管理信息
     * 
     * @param taskId 任务管理主键
     * @return 结果
     */
    @Override
    public int deleteGalleryTaskByTaskId(Long taskId)
    {
        return galleryTaskMapper.deleteGalleryTaskByTaskId(taskId);
    }
}
