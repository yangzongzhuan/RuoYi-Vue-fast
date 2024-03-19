package com.ruoyi.project.gallery.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.OSSUtils;
import com.ruoyi.common.utils.file.ImageUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.ruoyi.project.gallery.mapper.GalleryUserMapper;
import com.ruoyi.project.gallery.domain.GalleryUser;
import com.ruoyi.project.gallery.service.IGalleryUserService;
import org.springframework.web.multipart.MultipartFile;

import static com.ruoyi.common.utils.gallery.ImageUtils.*;

/**
 * 用户管理Service业务层处理
 *
 * @author ogcfun
 * @date 2024-02-27
 */
@Service
public class GalleryUserServiceImpl implements IGalleryUserService {
    @Autowired
    private GalleryUserMapper galleryUserMapper;

    @Autowired
    private OSSUtils ossUtils;
    private static final Logger log = LoggerFactory.getLogger(ImageUtils.class);

    @Value("${ruoyi.profile}")
    private String profile;

    /**
     * 查询用户管理
     *
     * @param userId 用户管理主键
     * @return 用户管理
     */
    @Override
    public GalleryUser selectGalleryUserByUserId(Long userId) {
        return galleryUserMapper.selectGalleryUserByUserId(userId);
    }

    /**
     * 查询用户管理列表
     *
     * @param galleryUser 用户管理
     * @return 用户管理
     */
    @Override
    public List<GalleryUser> selectGalleryUserList(GalleryUser galleryUser) {
        return galleryUserMapper.selectGalleryUserList(galleryUser);
    }

    /**
     * 新增用户管理
     *
     * @param galleryUser 用户管理
     * @return 结果
     */
    @Override
    public int insertGalleryUser(GalleryUser galleryUser) {
        return galleryUserMapper.insertGalleryUser(galleryUser);
    }

    /**
     * 修改用户管理
     *
     * @param galleryUser 用户管理
     * @return 结果
     */
    @Override
    public int updateGalleryUser(GalleryUser galleryUser) {
        return galleryUserMapper.updateGalleryUser(galleryUser);
    }

    /**
     * 批量删除用户管理
     *
     * @param userIds 需要删除的用户管理主键
     * @return 结果
     */
    @Override
    public int deleteGalleryUserByUserIds(Long[] userIds) {
        return galleryUserMapper.deleteGalleryUserByUserIds(userIds);
    }

    /**
     * 删除用户管理信息
     *
     * @param userId 用户管理主键
     * @return 结果
     */
    @Override
    public int deleteGalleryUserByUserId(Long userId) {
        return galleryUserMapper.deleteGalleryUserByUserId(userId);
    }

    /**
     * 查询用户信息
     *
     * @param userId 用户ID
     * @return 用户名称，头像地址
     */
    @Override
    public AjaxResult getGalleryUserByUserId(Long userId) {
        try {
            GalleryUser galleryUser = galleryUserMapper.selectGalleryUserByUserId(userId);
            if (galleryUser != null) {
                Map<String, String> list = new HashMap<>();
                String userName = galleryUser.getUserName();
                String userAvatar = galleryUser.getUserAvatar();
                list.put("userName", userName);
                list.put("userAvatar", userAvatar);
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
     * 上传用户头像
     *
     * @param file 用户头像文件
     * @return 用户头像地址
     */
    @Override
    public AjaxResult uploadGalleryUserByUserAvatar(MultipartFile file) {
        // 获取最终文件名
        String fileName = generateFileName(file);

        // 最终文件上传路径
        String filePath = "头像/" + fileName;

        try {
            // 将MultipartFile类型转化为File类型，并保存到本地
            File avatarFile = multipartFileToFile(file, profile, fileName);

            // 获取本地文件绝对地址
            String path = avatarFile.getPath();

            //创建ftp对象
            //FTPUtils ftpUtil = new FTPUtils();
            // 上传文件到FTP
            //FTPUtils.UploadStatus uploadStatus = ftpUtil.upload(path, filePath);

            // 如果上传到FTP成功，则删除本地缓存的文件
           /* if (uploadStatus == FTPUtils.UploadStatus.UploadNewFileSuccess ||
                    uploadStatus == FTPUtils.UploadStatus.UploadFromBreakSuccess) {
                deleteLocalFile(path);
            }*/

            // 上传文件到阿里云oss
            ossUtils.upload(file,filePath);

            //删除本地文件
            deleteLocalFile(path);

            // 返回最终文件地址
            return AjaxResult.success("上传成功", filePath);
        } catch (Exception e) {
            log.error("上传失败", e);
            return AjaxResult.error("上传失败");
        }
    }
}
