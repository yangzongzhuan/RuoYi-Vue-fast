package com.ruoyi.project.gallery.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.FTPUtil;
import com.ruoyi.common.utils.file.ImageUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.ruoyi.project.gallery.mapper.GalleryImageMapper;
import com.ruoyi.project.gallery.domain.GalleryImage;
import com.ruoyi.project.gallery.service.IGalleryImageService;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;

import static com.ruoyi.common.utils.gallery.ImageUtils.*;

/**
 * 列表管理Service业务层处理
 *
 * @author ogcfun
 * @date 2024-02-28
 */
@Service
public class GalleryImageServiceImpl implements IGalleryImageService {
    @Autowired
    private GalleryImageMapper galleryImageMapper;

    private static final Logger log = LoggerFactory.getLogger(ImageUtils.class);

    @Value("${ruoyi.profile}")
    private String profile;

    /**
     * 查询列表管理
     *
     * @param fileId 列表管理主键
     * @return 列表管理
     */
    @Override
    public GalleryImage selectGalleryImageByFileId(Long fileId) {
        return galleryImageMapper.selectGalleryImageByFileId(fileId);
    }

    /**
     * 查询列表管理列表
     *
     * @param galleryImage 列表管理
     * @return 列表管理
     */
    @Override
    public List<GalleryImage> selectGalleryImageList(GalleryImage galleryImage) {
        return galleryImageMapper.selectGalleryImageList(galleryImage);
    }

    /**
     * 新增列表管理
     *
     * @param galleryImage 列表管理
     * @return 结果
     */
    @Override
    public int insertGalleryImage(GalleryImage galleryImage) {
        return galleryImageMapper.insertGalleryImage(galleryImage);
    }

    /**
     * 修改列表管理
     *
     * @param galleryImage 列表管理
     * @return 结果
     */
    @Override
    public int updateGalleryImage(GalleryImage galleryImage) {
        return galleryImageMapper.updateGalleryImage(galleryImage);
    }

    /**
     * 批量删除列表管理
     *
     * @param fileIds 需要删除的列表管理主键
     * @return 结果
     */
    @Override
    public int deleteGalleryImageByFileIds(Long[] fileIds) {
        return galleryImageMapper.deleteGalleryImageByFileIds(fileIds);
    }

    /**
     * 删除列表管理信息
     *
     * @param fileId 列表管理主键
     * @return 结果
     */
    @Override
    public int deleteGalleryImageByFileId(Long fileId) {
        return galleryImageMapper.deleteGalleryImageByFileId(fileId);
    }

    /**
     * 上传/修改单张列表图片
     *
     * @param fileType 图片文件夹路径
     * @param file     图片文件
     * @return 图片数据信息
     */
    @Override
    public AjaxResult uploadGalleryImageByImage(String fileType, MultipartFile file) {
        // 获取最终文件名
        String fileName = generateFileName(file);
        // 最终文件上传路径
        String filePath = "/" + fileType + "/" + fileName;
        try {
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
            Map<String, String> result = new HashMap<>();
            result.put("width", width);
            result.put("height", height);
            result.put("suffix", suffix);
            result.put("fileSize", sizeString);
            result.put("fileName", fileName);
            result.put("filePath", filePath);

            return AjaxResult.success("成功", result);
        } catch (IOException e) {
            log.error("文件载入失败", e);
            return AjaxResult.error("文件载入失败");
        }
    }
}
