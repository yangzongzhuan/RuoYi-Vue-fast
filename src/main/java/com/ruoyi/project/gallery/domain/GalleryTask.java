package com.ruoyi.project.gallery.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 任务管理对象 gallery_task
 * 
 * @author ogcfun
 * @date 2024-02-29
 */
public class GalleryTask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 任务编号 */
    @Excel(name = "任务编号")
    private Long taskId;

    /** 用户编号 */
    @Excel(name = "用户编号")
    private Long userId;

    /** 记录点赞次数 */
    @Excel(name = "记录点赞次数")
    private Long taskLikeCount;

    /** 是否签到 0是没签 1是签到 */
    @Excel(name = "是否签到 0是没签 1是签到")
    private Integer taskSignIn;

    /** 是否领取奖励 0是没领 1是领取 */
    @Excel(name = "是否领取奖励 0是没领 1是领取")
    private Integer taskAward;

    /** 任务时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "任务时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date taskDate;

    public void setTaskId(Long taskId) 
    {
        this.taskId = taskId;
    }

    public Long getTaskId() 
    {
        return taskId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setTaskLikeCount(Long taskLikeCount) 
    {
        this.taskLikeCount = taskLikeCount;
    }

    public Long getTaskLikeCount() 
    {
        return taskLikeCount;
    }
    public void setTaskSignIn(Integer taskSignIn) 
    {
        this.taskSignIn = taskSignIn;
    }

    public Integer getTaskSignIn() 
    {
        return taskSignIn;
    }
    public void setTaskAward(Integer taskAward) 
    {
        this.taskAward = taskAward;
    }

    public Integer getTaskAward() 
    {
        return taskAward;
    }
    public void setTaskDate(Date taskDate) 
    {
        this.taskDate = taskDate;
    }

    public Date getTaskDate() 
    {
        return taskDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("taskId", getTaskId())
            .append("userId", getUserId())
            .append("taskLikeCount", getTaskLikeCount())
            .append("taskSignIn", getTaskSignIn())
            .append("taskAward", getTaskAward())
            .append("taskDate", getTaskDate())
            .toString();
    }
}
