package com.ruoyi.project.gallery.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 反馈管理对象 gallery_feedback
 * 
 * @author ogcfun
 * @date 2024-02-29
 */
public class GalleryFeedback extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 反馈编号 */
    @Excel(name = "反馈编号")
    private Long fbId;

    /** 用户编号 */
    @Excel(name = "用户编号")
    private Long userId;

    /** 用户账号 */
    @Excel(name = "用户账号")
    private String userName;

    /** 反馈信息 */
    @Excel(name = "反馈信息")
    private String fbDesc;

    /** 反馈图片 */
    @Excel(name = "反馈图片")
    private String fbImage;

    /** 反馈时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "反馈时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date fbDate;

    /** 反馈进度 0未解决 1解决中 2已解决 */
    @Excel(name = "反馈进度 0未解决 1解决中 2已解决")
    private Integer fbSchedule;

    public void setFbId(Long fbId) 
    {
        this.fbId = fbId;
    }

    public Long getFbId() 
    {
        return fbId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setFbDesc(String fbDesc) 
    {
        this.fbDesc = fbDesc;
    }

    public String getFbDesc() 
    {
        return fbDesc;
    }
    public void setFbImage(String fbImage) 
    {
        this.fbImage = fbImage;
    }

    public String getFbImage() 
    {
        return fbImage;
    }
    public void setFbDate(Date fbDate) 
    {
        this.fbDate = fbDate;
    }

    public Date getFbDate() 
    {
        return fbDate;
    }
    public void setFbSchedule(Integer fbSchedule) 
    {
        this.fbSchedule = fbSchedule;
    }

    public Integer getFbSchedule() 
    {
        return fbSchedule;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("fbId", getFbId())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("fbDesc", getFbDesc())
            .append("fbImage", getFbImage())
            .append("fbDate", getFbDate())
            .append("fbSchedule", getFbSchedule())
            .toString();
    }
}
