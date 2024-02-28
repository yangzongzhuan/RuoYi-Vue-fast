package com.ruoyi.project.gallery.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.gallery.mapper.GalleryUserMapper;
import com.ruoyi.project.gallery.domain.GalleryUser;
import com.ruoyi.project.gallery.service.IGalleryUserService;

/**
 * 用户管理Service业务层处理
 * 
 * @author ogcfun
 * @date 2024-02-27
 */
@Service
public class GalleryUserServiceImpl implements IGalleryUserService 
{
    @Autowired
    private GalleryUserMapper galleryUserMapper;

    /**
     * 查询用户管理
     * 
     * @param userId 用户管理主键
     * @return 用户管理
     */
    @Override
    public GalleryUser selectGalleryUserByUserId(Long userId)
    {
        return galleryUserMapper.selectGalleryUserByUserId(userId);
    }

    /**
     * 查询用户管理列表
     * 
     * @param galleryUser 用户管理
     * @return 用户管理
     */
    @Override
    public List<GalleryUser> selectGalleryUserList(GalleryUser galleryUser)
    {
        return galleryUserMapper.selectGalleryUserList(galleryUser);
    }

    /**
     * 新增用户管理
     * 
     * @param galleryUser 用户管理
     * @return 结果
     */
    @Override
    public int insertGalleryUser(GalleryUser galleryUser)
    {
        return galleryUserMapper.insertGalleryUser(galleryUser);
    }

    /**
     * 修改用户管理
     * 
     * @param galleryUser 用户管理
     * @return 结果
     */
    @Override
    public int updateGalleryUser(GalleryUser galleryUser)
    {
        return galleryUserMapper.updateGalleryUser(galleryUser);
    }

    /**
     * 批量删除用户管理
     * 
     * @param userIds 需要删除的用户管理主键
     * @return 结果
     */
    @Override
    public int deleteGalleryUserByUserIds(Long[] userIds)
    {
        return galleryUserMapper.deleteGalleryUserByUserIds(userIds);
    }

    /**
     * 删除用户管理信息
     * 
     * @param userId 用户管理主键
     * @return 结果
     */
    @Override
    public int deleteGalleryUserByUserId(Long userId)
    {
        return galleryUserMapper.deleteGalleryUserByUserId(userId);
    }
}
