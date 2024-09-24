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
import com.ruoyi.project.gallery.domain.GalleryLoginLog;
import com.ruoyi.project.gallery.service.IGalleryLoginLogService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 登录日志Controller
 * 
 * @author ogcfun
 * @date 2024-03-04
 */
@RestController
@RequestMapping("/gallery/galleryLoginLog")
public class GalleryLoginLogController extends BaseController
{
    @Autowired
    private IGalleryLoginLogService galleryLoginLogService;

    /**
     * 查询登录日志列表
     */
    @PreAuthorize("@ss.hasPermi('gallery:galleryLoginLog:list')")
    @GetMapping("/list")
    public TableDataInfo list(GalleryLoginLog galleryLoginLog)
    {
        startPage();
        List<GalleryLoginLog> list = galleryLoginLogService.selectGalleryLoginLogList(galleryLoginLog);
        return getDataTable(list);
    }

    /**
     * 导出登录日志列表
     */
    @PreAuthorize("@ss.hasPermi('gallery:galleryLoginLog:export')")
    @Log(title = "登录日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GalleryLoginLog galleryLoginLog)
    {
        List<GalleryLoginLog> list = galleryLoginLogService.selectGalleryLoginLogList(galleryLoginLog);
        ExcelUtil<GalleryLoginLog> util = new ExcelUtil<GalleryLoginLog>(GalleryLoginLog.class);
        util.exportExcel(response, list, "登录日志数据");
    }

    /**
     * 获取登录日志详细信息
     */
    @PreAuthorize("@ss.hasPermi('gallery:galleryLoginLog:query')")
    @GetMapping(value = "/{logId}")
    public AjaxResult getInfo(@PathVariable("logId") Long logId)
    {
        return success(galleryLoginLogService.selectGalleryLoginLogByLogId(logId));
    }

    /**
     * 新增登录日志
     */
    @PreAuthorize("@ss.hasPermi('gallery:galleryLoginLog:add')")
    @Log(title = "登录日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GalleryLoginLog galleryLoginLog)
    {
        return toAjax(galleryLoginLogService.insertGalleryLoginLog(galleryLoginLog));
    }

    /**
     * 修改登录日志
     */
    @PreAuthorize("@ss.hasPermi('gallery:galleryLoginLog:edit')")
    @Log(title = "登录日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GalleryLoginLog galleryLoginLog)
    {
        return toAjax(galleryLoginLogService.updateGalleryLoginLog(galleryLoginLog));
    }

    /**
     * 删除登录日志
     */
    @PreAuthorize("@ss.hasPermi('gallery:galleryLoginLog:remove')")
    @Log(title = "登录日志", businessType = BusinessType.DELETE)
	@DeleteMapping("/{logIds}")
    public AjaxResult remove(@PathVariable Long[] logIds)
    {
        return toAjax(galleryLoginLogService.deleteGalleryLoginLogByLogIds(logIds));
    }
}
