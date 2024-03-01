package com.ruoyi.project.gallery.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.FTPUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
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

    /**
     *  上传头像文件到ftp
     * @param file 头像文件
     * @return 文件地址
     */
    @PostMapping("/uploadAvatar")
    public AjaxResult upload(MultipartFile file) throws IOException {
        // 文件名
        String fileName = file.getOriginalFilename();
        // 获取原始文件的后缀名
        String suffix = getSuffix(fileName);
        // 生成随机六位字符串
        String randomString = generateRandomString(6);
        // 获取当前时间戳
        long timestamp = System.currentTimeMillis();
        // 构造最终文件名
        fileName = "/头像/" + randomString + "_" + timestamp + suffix;

        // 将MultipartFile类型转化为File类型，并保存到本地
        File avatarFile = multipartFileToFile(file,"C:\\temp",randomString);
        // 获取文件绝对地址
        String path = avatarFile.getPath();

        //创建ftp对象
        FTPUtil ftpUtil = new FTPUtil();
        // 上传文件到FTP
        FTPUtil.UploadStatus upload = ftpUtil.upload(path, fileName);

        // 创建集合添加返回数据
        Map<String, Object> result = new HashMap<>();
        // 在上传过程中设置返回值
        result.put("uploadStatus", upload);
        result.put("userAvatar", fileName);

        // 返回最终文件地址
        return AjaxResult.success("上传成功",result);
    }

    /**
     * 将MultipartFile类型转化为File类型，并保存到本地
     * @param multipartFile 前端传入文件
     * @param directory 保存的文件夹
     * @param fileName 文件名称
     * @return file类型文件
     * @throws IOException 报错处理
     */
    public static File multipartFileToFile(MultipartFile multipartFile, String directory, String fileName) throws IOException {
        // 确定文件保存的目录路径
        Path directoryPath = Paths.get(directory);
        if (!Files.exists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }

        // 构造文件的完整路径
        Path filePath = Paths.get(directory, fileName);

        // 将 MultipartFile 内容写入到文件中
        File file = filePath.toFile();
        FileCopyUtils.copy(multipartFile.getBytes(), file);

        return file;
    }

    /**
     * 获取文件后缀名的方法
     * @param fileName 文件名
     * @return 文件后缀
     */
    private static String getSuffix(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex != -1) {
            return fileName.substring(dotIndex);
        }
        // 如果文件没有后缀名，则返回一个默认的后缀名，比如 ".png" 或者 ".dat"
        return ".png";
    }

    /**
     *  生成随机字符串
     * @param length 长度
     * @return 随机字符串
     */
    private static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }
}
