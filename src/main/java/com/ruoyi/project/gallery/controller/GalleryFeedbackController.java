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
import com.ruoyi.project.gallery.domain.GalleryFeedback;
import com.ruoyi.project.gallery.service.IGalleryFeedbackService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 反馈管理Controller
 * 
 * @author ogcfun
 * @date 2024-02-29
 */
@RestController
@RequestMapping("/gallery/galleryFeedback")
public class GalleryFeedbackController extends BaseController
{
    @Autowired
    private IGalleryFeedbackService galleryFeedbackService;

    /**
     * 查询反馈管理列表
     */
    @PreAuthorize("@ss.hasPermi('gallery:galleryFeedback:list')")
    @GetMapping("/list")
    public TableDataInfo list(GalleryFeedback galleryFeedback)
    {
        startPage();
        List<GalleryFeedback> list = galleryFeedbackService.selectGalleryFeedbackList(galleryFeedback);
        return getDataTable(list);
    }

    /**
     * 导出反馈管理列表
     */
    @PreAuthorize("@ss.hasPermi('gallery:galleryFeedback:export')")
    @Log(title = "反馈管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GalleryFeedback galleryFeedback)
    {
        List<GalleryFeedback> list = galleryFeedbackService.selectGalleryFeedbackList(galleryFeedback);
        ExcelUtil<GalleryFeedback> util = new ExcelUtil<GalleryFeedback>(GalleryFeedback.class);
        util.exportExcel(response, list, "反馈管理数据");
    }

    /**
     * 获取反馈管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('gallery:galleryFeedback:query')")
    @GetMapping(value = "/{fbId}")
    public AjaxResult getInfo(@PathVariable("fbId") Long fbId)
    {
        return success(galleryFeedbackService.selectGalleryFeedbackByFbId(fbId));
    }

    /**
     * 新增反馈管理
     */
    @PreAuthorize("@ss.hasPermi('gallery:galleryFeedback:add')")
    @Log(title = "反馈管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GalleryFeedback galleryFeedback)
    {
        return toAjax(galleryFeedbackService.insertGalleryFeedback(galleryFeedback));
    }

    /**
     * 修改反馈管理
     */
    @PreAuthorize("@ss.hasPermi('gallery:galleryFeedback:edit')")
    @Log(title = "反馈管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GalleryFeedback galleryFeedback)
    {
        return toAjax(galleryFeedbackService.updateGalleryFeedback(galleryFeedback));
    }

    /**
     * 删除反馈管理
     */
    @PreAuthorize("@ss.hasPermi('gallery:galleryFeedback:remove')")
    @Log(title = "反馈管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{fbIds}")
    public AjaxResult remove(@PathVariable Long[] fbIds)
    {
        return toAjax(galleryFeedbackService.deleteGalleryFeedbackByFbIds(fbIds));
    }
}
