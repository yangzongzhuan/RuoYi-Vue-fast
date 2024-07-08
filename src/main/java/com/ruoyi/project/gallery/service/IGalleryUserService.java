package com.ruoyi.project.gallery.service;

import java.util.List;

import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.gallery.domain.GalleryUser;
import org.springframework.web.multipart.MultipartFile;

/**
 * 用户管理Service接口
 * 
 * @author ogcfun
 * @date 2024-02-27
 */
public interface IGalleryUserService 
{
    /**
     * 查询用户管理
     * 
     * @param userId 用户管理主键
     * @return 用户管理
     */
    public GalleryUser selectGalleryUserByUserId(Long userId);

    /**
     * 查询用户管理列表
     * 
     * @param galleryUser 用户管理
     * @return 用户管理集合
     */
    public List<GalleryUser> selectGalleryUserList(GalleryUser galleryUser);

    /**
     * 新增用户管理
     * 
     * @param galleryUser 用户管理
     * @return 结果
     */
    public int insertGalleryUser(GalleryUser galleryUser);

    /**
     * 修改用户管理
     * 
     * @param galleryUser 用户管理
     * @return 结果
     */
    public int updateGalleryUser(GalleryUser galleryUser);

    /**
     * 批量删除用户管理
     * 
     * @param userIds 需要删除的用户管理主键集合
     * @return 结果
     */
    public int deleteGalleryUserByUserIds(Long[] userIds);

    /**
     * 删除用户管理信息
     * 
     * @param userId 用户管理主键
     * @return 结果
     */
    public int deleteGalleryUserByUserId(Long userId);

    /**
     * 查询用户信息
     *
     * @param userId 用户ID
     * @return 用户名称，头像地址
     */
    public AjaxResult getGalleryUserByUserId(Long userId);

    /**
     * 上传用户头像
     *
     * @param file 用户头像文件
     * @return 用户头像地址
     */
    public AjaxResult uploadGalleryUserByUserAvatar(MultipartFile file);

}
