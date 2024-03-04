package com.ruoyi.project.gallery.controller;

import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.FTPUtil;
import com.ruoyi.common.utils.file.ImageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.multipart.MultipartFile;
import static com.ruoyi.common.utils.gallery.ImageUtils.*;

/**
 * 用户管理Controller
 *
 * @author ogcfun
 * @date 2024-02-27
 */
@RestController
@RequestMapping("/gallery/galleryUser")
public class GalleryUserController extends BaseController {
    @Autowired
    private IGalleryUserService galleryUserService;

    private static final Logger log = LoggerFactory.getLogger(ImageUtils.class);

    @Value("${ruoyi.profile}")
    private String profile;

    /**
     * 查询用户管理列表
     */
    @PreAuthorize("@ss.hasPermi('gallery:galleryUser:list')")
    @GetMapping("/list")
    public TableDataInfo list(GalleryUser galleryUser) {
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
    public void export(HttpServletResponse response, GalleryUser galleryUser) {
        List<GalleryUser> list = galleryUserService.selectGalleryUserList(galleryUser);
        ExcelUtil<GalleryUser> util = new ExcelUtil<GalleryUser>(GalleryUser.class);
        util.exportExcel(response, list, "用户管理数据");
    }

    /**
     * 获取用户管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('gallery:galleryUser:query')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId) {
        return success(galleryUserService.selectGalleryUserByUserId(userId));
    }

    /**
     * 新增用户管理
     */
    @PreAuthorize("@ss.hasPermi('gallery:galleryUser:add')")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GalleryUser galleryUser) {
        return toAjax(galleryUserService.insertGalleryUser(galleryUser));
    }

    /**
     * 修改用户管理
     */
    @PreAuthorize("@ss.hasPermi('gallery:galleryUser:edit')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GalleryUser galleryUser) {
        return toAjax(galleryUserService.updateGalleryUser(galleryUser));
    }

    /**
     * 删除用户管理
     */
    @PreAuthorize("@ss.hasPermi('gallery:galleryUser:remove')")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds) {
        return toAjax(galleryUserService.deleteGalleryUserByUserIds(userIds));
    }

    /**
     * 根据用户id查找名称和头像
     * @param userId 用户id
     * @return 名称和头像
     */
    @GetMapping("/userInfo/{userId}")
    public AjaxResult getUserId(@PathVariable Long userId) {
        try {
            GalleryUser galleryUser = galleryUserService.selectGalleryUserByUserId(userId);
            if (galleryUser != null) {
                Map<String,String> list = new HashMap<>();
                String userName = galleryUser.getUserName();
                String userAvatar = galleryUser.getUserAvatar();
                list.put("userName",userName);
                list.put("userAvatar",userAvatar);
                return AjaxResult.success(list);
            } else {
                return AjaxResult.error("未找到用户");
            }
        } catch (Exception e) {
            log.error("获取用户信息失败", e);
            return AjaxResult.error("获取用户信息失败");
        }
    }

    /**
     * 上传头像文件到ftp
     *
     * @param file 头像文件
     * @return 文件地址
     */
    @PostMapping("/uploadAvatar")
    public AjaxResult upload(MultipartFile file) throws IOException {
        try {
            // 获取最终文件名
            String fileName = generateFileName(file);

            // 最终文件上传路径
            String filePath = "/头像/" + fileName;

            // 将MultipartFile类型转化为File类型，并保存到本地
            File avatarFile = multipartFileToFile(file, profile, fileName);

            // 获取本地文件绝对地址
            String path = avatarFile.getPath();

            //创建ftp对象
            FTPUtil ftpUtil = new FTPUtil();
            // 上传文件到FTP
            FTPUtil.UploadStatus uploadStatus = ftpUtil.upload(path, filePath);

            // 如果上传到FTP成功，则删除本地缓存的文件
            if (uploadStatus == FTPUtil.UploadStatus.UploadNewFileSuccess ||
                    uploadStatus == FTPUtil.UploadStatus.UploadFromBreakSuccess) {
                deleteLocalFile(path);
            }

            // 返回最终文件地址
            return AjaxResult.success("上传成功", filePath);
        } catch (Exception e) {
            log.error("上传失败", e);
            return AjaxResult.error("上传失败");
        }
    }

}
