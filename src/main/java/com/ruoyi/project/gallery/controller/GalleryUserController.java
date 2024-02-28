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
import com.ruoyi.project.gallery.domain.GalleryUser;
import com.ruoyi.project.gallery.service.IGalleryUserService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 用户管理Controller
 * 
 * @author ogcfun
 * @date 2024-02-27
 */
@RestController
@RequestMapping("/gallery/galleryUser")
public class GalleryUserController extends BaseController
{
    @Autowired
    private IGalleryUserService galleryUserService;

    /**
     * 查询用户管理列表
     */
    @PreAuthorize("@ss.hasPermi('gallery:galleryUser:list')")
    @GetMapping("/list")
    public TableDataInfo list(GalleryUser galleryUser)
    {
        startPage();
        List<GalleryUser> list = galleryUserService.selectGalleryUserList(galleryUser);
        return getDataTable(list);
    }

    /**
     * 导出用户管理列表
     */
    @PreAuthorize("@ss.hasPermi('gallery:galleryUser:export')")
    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GalleryUser galleryUser)
    {
        List<GalleryUser> list = galleryUserService.selectGalleryUserList(galleryUser);
        ExcelUtil<GalleryUser> util = new ExcelUtil<GalleryUser>(GalleryUser.class);
        util.exportExcel(response, list, "用户管理数据");
    }

    /**
     * 获取用户管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('gallery:galleryUser:query')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId)
    {
        return success(galleryUserService.selectGalleryUserByUserId(userId));
    }

    /**
     * 新增用户管理
     */
    @PreAuthorize("@ss.hasPermi('gallery:galleryUser:add')")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GalleryUser galleryUser)
    {
        return toAjax(galleryUserService.insertGalleryUser(galleryUser));
    }

    /**
     * 修改用户管理
     */
    @PreAuthorize("@ss.hasPermi('gallery:galleryUser:edit')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GalleryUser galleryUser)
    {
        return toAjax(galleryUserService.updateGalleryUser(galleryUser));
    }

    /**
     * 删除用户管理
     */
    @PreAuthorize("@ss.hasPermi('gallery:galleryUser:remove')")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        return toAjax(galleryUserService.deleteGalleryUserByUserIds(userIds));
    }
}
