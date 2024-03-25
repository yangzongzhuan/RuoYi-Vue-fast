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
import com.ruoyi.project.gallery.domain.GalleryType;
import com.ruoyi.project.gallery.service.IGalleryTypeService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 分类管理Controller
 * 
 * @author ogcfun
 * @date 2024-02-28
 */
@RestController
@RequestMapping("/gallery/galleryType")
public class GalleryTypeController extends BaseController
{
    @Autowired
    private IGalleryTypeService galleryTypeService;

    /**
     * 查询分类管理列表
     */
    @PreAuthorize("@ss.hasPermi('gallery:galleryType:list')")
    @GetMapping("/list")
    public TableDataInfo list(GalleryType galleryType)
    {
        startPage();
        List<GalleryType> list = galleryTypeService.selectGalleryTypeList(galleryType);
        return getDataTable(list);
    }

    /**
     * 导出分类管理列表
     */
    @PreAuthorize("@ss.hasPermi('gallery:galleryType:export')")
    @Log(title = "分类管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GalleryType galleryType)
    {
        List<GalleryType> list = galleryTypeService.selectGalleryTypeList(galleryType);
        ExcelUtil<GalleryType> util = new ExcelUtil<GalleryType>(GalleryType.class);
        util.exportExcel(response, list, "分类管理数据");
    }

    /**
     * 获取分类管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('gallery:galleryType:query')")
    @GetMapping(value = "/{typeId}")
    public AjaxResult getInfo(@PathVariable("typeId") Long typeId)
    {
        return success(galleryTypeService.selectGalleryTypeByTypeId(typeId));
    }

    /**
     * 新增分类管理
     */
    @PreAuthorize("@ss.hasPermi('gallery:galleryType:add')")
    @Log(title = "分类管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GalleryType galleryType)
    {
        return toAjax(galleryTypeService.insertGalleryType(galleryType));
    }

    /**
     * 修改分类管理
     */
    @PreAuthorize("@ss.hasPermi('gallery:galleryType:edit')")
    @Log(title = "分类管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GalleryType galleryType)
    {
        return toAjax(galleryTypeService.updateGalleryType(galleryType));
    }

    /**
     * 删除分类管理
     */
    @PreAuthorize("@ss.hasPermi('gallery:galleryType:remove')")
    @Log(title = "分类管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{typeIds}")
    public AjaxResult remove(@PathVariable Long[] typeIds)
    {
        return toAjax(galleryTypeService.deleteGalleryTypeByTypeIds(typeIds));
    }
}
