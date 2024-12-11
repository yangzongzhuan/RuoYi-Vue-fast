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
import com.ruoyi.project.gallery.domain.GalleryCollectLike;
import com.ruoyi.project.gallery.service.IGalleryCollectLikeService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 收藏管理Controller
 * 
 * @author ogcfun
 * @date 2024-02-29
 */
@RestController
@RequestMapping("/gallery/galleryCollectLike")
public class GalleryCollectLikeController extends BaseController
{
    @Autowired
    private IGalleryCollectLikeService galleryCollectLikeService;

    /**
     * 查询收藏管理列表
     */
    @PreAuthorize("@ss.hasPermi('gallery:galleryCollectLike:list')")
    @GetMapping("/list")
    public TableDataInfo list(GalleryCollectLike galleryCollectLike)
    {
        startPage();
        List<GalleryCollectLike> list = galleryCollectLikeService.selectGalleryCollectLikeList(galleryCollectLike);
        return getDataTable(list);
    }

    /**
     * 导出收藏管理列表
     */
    @PreAuthorize("@ss.hasPermi('gallery:galleryCollectLike:export')")
    @Log(title = "收藏管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GalleryCollectLike galleryCollectLike)
    {
        List<GalleryCollectLike> list = galleryCollectLikeService.selectGalleryCollectLikeList(galleryCollectLike);
        ExcelUtil<GalleryCollectLike> util = new ExcelUtil<GalleryCollectLike>(GalleryCollectLike.class);
        util.exportExcel(response, list, "收藏管理数据");
    }

    /**
     * 获取收藏管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('gallery:galleryCollectLike:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(galleryCollectLikeService.selectGalleryCollectLikeById(id));
    }

    /**
     * 新增收藏管理
     */
    @PreAuthorize("@ss.hasPermi('gallery:galleryCollectLike:add')")
    @Log(title = "收藏管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GalleryCollectLike galleryCollectLike)
    {
        return toAjax(galleryCollectLikeService.insertGalleryCollectLike(galleryCollectLike));
    }

    /**
     * 修改收藏管理
     */
    @PreAuthorize("@ss.hasPermi('gallery:galleryCollectLike:edit')")
    @Log(title = "收藏管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GalleryCollectLike galleryCollectLike)
    {
        return toAjax(galleryCollectLikeService.updateGalleryCollectLike(galleryCollectLike));
    }

    /**
     * 删除收藏管理
     */
    @PreAuthorize("@ss.hasPermi('gallery:galleryCollectLike:remove')")
    @Log(title = "收藏管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(galleryCollectLikeService.deleteGalleryCollectLikeByIds(ids));
    }
}
