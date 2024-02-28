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
import com.ruoyi.project.gallery.domain.GalleryImage;
import com.ruoyi.project.gallery.service.IGalleryImageService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 列表管理Controller
 * 
 * @author ogcfun
 * @date 2024-02-28
 */
@RestController
@RequestMapping("/gallery/galleryImage")
public class GalleryImageController extends BaseController
{
    @Autowired
    private IGalleryImageService galleryImageService;

    /**
     * 查询列表管理列表
     */
    @PreAuthorize("@ss.hasPermi('gallery:galleryImage:list')")
    @GetMapping("/list")
    public TableDataInfo list(GalleryImage galleryImage)
    {
        startPage();
        List<GalleryImage> list = galleryImageService.selectGalleryImageList(galleryImage);
        return getDataTable(list);
    }

    /**
     * 导出列表管理列表
     */
    @PreAuthorize("@ss.hasPermi('gallery:galleryImage:export')")
    @Log(title = "列表管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GalleryImage galleryImage)
    {
        List<GalleryImage> list = galleryImageService.selectGalleryImageList(galleryImage);
        ExcelUtil<GalleryImage> util = new ExcelUtil<GalleryImage>(GalleryImage.class);
        util.exportExcel(response, list, "列表管理数据");
    }

    /**
     * 获取列表管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('gallery:galleryImage:query')")
    @GetMapping(value = "/{fileId}")
    public AjaxResult getInfo(@PathVariable("fileId") Long fileId)
    {
        return success(galleryImageService.selectGalleryImageByFileId(fileId));
    }

    /**
     * 新增列表管理
     */
    @PreAuthorize("@ss.hasPermi('gallery:galleryImage:add')")
    @Log(title = "列表管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GalleryImage galleryImage)
    {
        return toAjax(galleryImageService.insertGalleryImage(galleryImage));
    }

    /**
     * 修改列表管理
     */
    @PreAuthorize("@ss.hasPermi('gallery:galleryImage:edit')")
    @Log(title = "列表管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GalleryImage galleryImage)
    {
        return toAjax(galleryImageService.updateGalleryImage(galleryImage));
    }

    /**
     * 删除列表管理
     */
    @PreAuthorize("@ss.hasPermi('gallery:galleryImage:remove')")
    @Log(title = "列表管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{fileIds}")
    public AjaxResult remove(@PathVariable Long[] fileIds)
    {
        return toAjax(galleryImageService.deleteGalleryImageByFileIds(fileIds));
    }
}
