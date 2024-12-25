package com.ruoyi.project.gallery.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.gallery.domain.GalleryTask;
import com.ruoyi.project.gallery.service.IGalleryTaskService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 任务管理Controller
 * 
 * @author ogcfun
 * @date 2024-02-29
 */
@RestController
@RequestMapping("/gallery/galleryTask")
public class GalleryTaskController extends BaseController
{
    @Autowired
    private IGalleryTaskService galleryTaskService;

    /**
     * 查询任务管理列表
     */
    @PreAuthorize("@ss.hasPermi('gallery:galleryTask:list')")
    @GetMapping("/list")
    public TableDataInfo list(GalleryTask galleryTask)
    {
        startPage();
        List<GalleryTask> list = galleryTaskService.selectGalleryTaskList(galleryTask);
        return getDataTable(list);
    }

    /**
     * 导出任务管理列表
     */
    @PreAuthorize("@ss.hasPermi('gallery:galleryTask:export')")
    @Log(title = "任务管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GalleryTask galleryTask)
    {
        List<GalleryTask> list = galleryTaskService.selectGalleryTaskList(galleryTask);
        ExcelUtil<GalleryTask> util = new ExcelUtil<GalleryTask>(GalleryTask.class);
        util.exportExcel(response, list, "任务管理数据");
    }

    /**
     * 获取任务管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('gallery:galleryTask:query')")
    @GetMapping(value = "/{taskId}")
    public AjaxResult getInfo(@PathVariable("taskId") Long taskId)
    {
        return success(galleryTaskService.selectGalleryTaskByTaskId(taskId));
    }

    /**
     * 新增任务管理
     */
    @PreAuthorize("@ss.hasPermi('gallery:galleryTask:add')")
    @Log(title = "任务管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GalleryTask galleryTask)
    {
        return toAjax(galleryTaskService.insertGalleryTask(galleryTask));
    }

    /**
     * 修改任务管理
     */
    @PreAuthorize("@ss.hasPermi('gallery:galleryTask:edit')")
    @Log(title = "任务管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GalleryTask galleryTask)
    {
        return toAjax(galleryTaskService.updateGalleryTask(galleryTask));
    }

    /**
     * 删除任务管理
     */
    @PreAuthorize("@ss.hasPermi('gallery:galleryTask:remove')")
    @Log(title = "任务管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{taskIds}")
    public AjaxResult remove(@PathVariable Long[] taskIds)
    {
        return toAjax(galleryTaskService.deleteGalleryTaskByTaskIds(taskIds));
    }
}
