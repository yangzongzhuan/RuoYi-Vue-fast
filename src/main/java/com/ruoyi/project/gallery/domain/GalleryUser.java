package com.ruoyi.project.gallery.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 用户管理对象 gallery_user
 * 
 * @author ogcfun
 * @date 2024-02-27
 */
public class GalleryUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户id */
    private Long userId;

    /** 微信用户在开放平台的唯一标识符 */
    private String userUnionid;

    /** 用户积分 兑换下载上传次数 */
    @Excel(name = "用户积分 兑换下载上传次数")
    private Long userIntegral;

    /** 用户名称 */
    @Excel(name = "用户名称")
    private String userName;

    /** 用户账号 */
    @Excel(name = "用户账号")
    private String userAccount;

    /** 用户密码 */
    private String userPassword;

    /** 用户头像 */
    @Excel(name = "用户头像")
    private String userAvatar;

    /** 用户邮箱 */
    private String userEmail;

    /** 用户qq */
    @Excel(name = "用户qq")
    private String userQq;

    /** 用户性别 0女 1男 2未设置 */
    @Excel(name = "用户性别 0女 1男 2未设置")
    private Integer userSex;

    /** 用户收藏数量 */
    @Excel(name = "用户收藏数量")
    private Long userCollect;

    /** 用户剩余下载次数 */
    private Long userDownCount;

    /** 用户剩余上传次数 */
    private Long userUploadCount;

    /** 账号创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "账号创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    /** 账号修改时间 */
    private Date updateDate;

    /** 用户账号状态 0正常 1禁用 */
    @Excel(name = "用户账号状态 0正常 1禁用")
    private Integer userStatus;

    /** 剩下的修改账号次数 */
    private Long userAccountNumber;

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setUserUnionid(String userUnionid) 
    {
        this.userUnionid = userUnionid;
    }

    public String getUserUnionid() 
    {
        return userUnionid;
    }
    public void setUserIntegral(Long userIntegral) 
    {
        this.userIntegral = userIntegral;
    }

    public Long getUserIntegral() 
    {
        return userIntegral;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setUserAccount(String userAccount) 
    {
        this.userAccount = userAccount;
    }

    public String getUserAccount() 
    {
        return userAccount;
    }
    public void setUserPassword(String userPassword) 
    {
        this.userPassword = userPassword;
    }

    public String getUserPassword() 
    {
        return userPassword;
    }
    public void setUserAvatar(String userAvatar) 
    {
        this.userAvatar = userAvatar;
    }

    public String getUserAvatar() 
    {
        return userAvatar;
    }
    public void setUserEmail(String userEmail) 
    {
        this.userEmail = userEmail;
    }

    public String getUserEmail() 
    {
        return userEmail;
    }
    public void setUserQq(String userQq) 
    {
        this.userQq = userQq;
    }

    public String getUserQq() 
    {
        return userQq;
    }
    public void setUserSex(Integer userSex) 
    {
        this.userSex = userSex;
    }

    public Integer getUserSex() 
    {
        return userSex;
    }
    public void setUserCollect(Long userCollect) 
    {
        this.userCollect = userCollect;
    }

    public Long getUserCollect() 
    {
        return userCollect;
    }
    public void setUserDownCount(Long userDownCount) 
    {
        this.userDownCount = userDownCount;
    }

    public Long getUserDownCount() 
    {
        return userDownCount;
    }
    public void setUserUploadCount(Long userUploadCount) 
    {
        this.userUploadCount = userUploadCount;
    }

    public Long getUserUploadCount() 
    {
        return userUploadCount;
    }
    public void setCreateDate(Date createDate) 
    {
        this.createDate = createDate;
    }

    public Date getCreateDate() 
    {
        return createDate;
    }
    public void setUpdateDate(Date updateDate) 
    {
        this.updateDate = updateDate;
    }

    public Date getUpdateDate() 
    {
        return updateDate;
    }
    public void setUserStatus(Integer userStatus) 
    {
        this.userStatus = userStatus;
    }

    public Integer getUserStatus() 
    {
        return userStatus;
    }
    public void setUserAccountNumber(Long userAccountNumber) 
    {
        this.userAccountNumber = userAccountNumber;
    }

    public Long getUserAccountNumber() 
    {
        return userAccountNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getUserId())
            .append("userUnionid", getUserUnionid())
            .append("userIntegral", getUserIntegral())
            .append("userName", getUserName())
            .append("userAccount", getUserAccount())
            .append("userPassword", getUserPassword())
            .append("userAvatar", getUserAvatar())
            .append("userEmail", getUserEmail())
            .append("userQq", getUserQq())
            .append("userSex", getUserSex())
            .append("userCollect", getUserCollect())
            .append("userDownCount", getUserDownCount())
            .append("userUploadCount", getUserUploadCount())
            .append("createDate", getCreateDate())
            .append("updateDate", getUpdateDate())
            .append("userStatus", getUserStatus())
            .append("userAccountNumber", getUserAccountNumber())
            .toString();
    }
}
