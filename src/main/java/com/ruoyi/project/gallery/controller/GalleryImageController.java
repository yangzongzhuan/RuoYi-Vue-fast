package com.ruoyi.project.gallery.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.FTPUtil;
import com.ruoyi.common.utils.file.ImageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.gallery.domain.GalleryImage;
import com.ruoyi.project.gallery.service.IGalleryImageService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import static com.ruoyi.common.utils.gallery.ImageUtils.*;

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

    private static final Logger log = LoggerFactory.getLogger(ImageUtils.class);

    @Value("${ruoyi.profile}")
    private String profile;

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

    /**
     * 新增图片列表
     * @param fileType 图片分类
     * @param file 图片文件
     * @return AjaxResult
     * @throws IOException 报错信息
     */
    @PostMapping("/uploadImage")
    public AjaxResult uploadImage(@RequestParam String fileType , MultipartFile file) throws IOException {
        try {
            // 获取最终文件名
            String fileName = generateFileName(file);
            // 最终文件上传路径
            String filePath = "/" + fileType + "/" + fileName;

            // 将MultipartFile类型转化为File类型，并保存到本地
            File imageFile = multipartFileToFile(file, profile, fileName);
            // 获取本地文件绝对地址
            String path = imageFile.getPath();
            // 获取图像文件的大小（以字节为单位）
            long fileSize = imageFile.length();
            // 将字节数转换为合适的单位（KB、MB 等）
            String sizeString = humanReadableByteCountBin(fileSize);

            // 将File转换为BufferedImage
            InputStream inputStream = Files.newInputStream(new File(path).toPath());
            BufferedImage bufferedImage = ImageIO.read(inputStream);
            // 获取图片的宽度和高度
            String width = String.valueOf(bufferedImage.getWidth());
            String height = String.valueOf(bufferedImage.getHeight());

            //创建ftp对象
            FTPUtil ftpUtil = new FTPUtil();
            // 上传文件到FTP
            FTPUtil.UploadStatus uploadStatus = ftpUtil.upload(path, filePath);

            // 如果上传到FTP成功，则删除本地缓存的文件
            if (uploadStatus == FTPUtil.UploadStatus.UploadNewFileSuccess ||
                    uploadStatus == FTPUtil.UploadStatus.UploadFromBreakSuccess) {
                deleteLocalFile(path);
            }

            // 获取文件后缀
            String suffix = getSuffix(fileName);

            // 创建集合保存返回信息
            Map<String,String> result = new HashMap<>();
            result.put("width",width);
            result.put("height",height);
            result.put("suffix",suffix);
            result.put("fileSize",sizeString);
            result.put("fileName",fileName);
            result.put("filePath",filePath);

            return AjaxResult.success("成功",result);
        } catch (IOException e) {
            log.error("文件载入失败",e);
            return AjaxResult.error("文件载入失败");
        }
    }
}
