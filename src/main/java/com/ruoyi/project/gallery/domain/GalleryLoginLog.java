package com.ruoyi.project.gallery.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 登录日志对象 gallery_login_log
 * 
 * @author ogcfun
 * @date 2024-03-04
 */
public class GalleryLoginLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 日志编号 */
    @Excel(name = "日志编号")
    private Long logId;

    /** 登录用户名称 */
    @Excel(name = "登录用户名称")
    private String userAccount;

    /** 登录Ip地址 */
    @Excel(name = "登录Ip地址")
    private String logIp;

    /** 登录城市 */
    @Excel(name = "登录城市")
    private String logCity;

    /** 登录省份 */
    @Excel(name = "登录省份")
    private String logRegionname;

    /** 登录国家 */
    @Excel(name = "登录国家")
    private String logCountry;

    /** 登录设备操作系统 */
    @Excel(name = "登录设备操作系统")
    private String logOs;

    /** 登录设备 */
    @Excel(name = "登录设备")
    private String logBroswer;

    /** 登录时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "登录时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date logDate;

    public void setLogId(Long logId) 
    {
        this.logId = logId;
    }

    public Long getLogId() 
    {
        return logId;
    }
    public void setUserAccount(String userAccount) 
    {
        this.userAccount = userAccount;
    }

    public String getUserAccount() 
    {
        return userAccount;
    }
    public void setLogIp(String logIp) 
    {
        this.logIp = logIp;
    }

    public String getLogIp() 
    {
        return logIp;
    }
    public void setLogCity(String logCity) 
    {
        this.logCity = logCity;
    }

    public String getLogCity() 
    {
        return logCity;
    }
    public void setLogRegionname(String logRegionname) 
    {
        this.logRegionname = logRegionname;
    }

    public String getLogRegionname() 
    {
        return logRegionname;
    }
    public void setLogCountry(String logCountry) 
    {
        this.logCountry = logCountry;
    }

    public String getLogCountry() 
    {
        return logCountry;
    }
    public void setLogOs(String logOs) 
    {
        this.logOs = logOs;
    }

    public String getLogOs() 
    {
        return logOs;
    }
    public void setLogBroswer(String logBroswer) 
    {
        this.logBroswer = logBroswer;
    }

    public String getLogBroswer() 
    {
        return logBroswer;
    }
    public void setLogDate(Date logDate) 
    {
        this.logDate = logDate;
    }

    public Date getLogDate() 
    {
        return logDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("logId", getLogId())
            .append("userAccount", getUserAccount())
            .append("logIp", getLogIp())
            .append("logCity", getLogCity())
            .append("logRegionname", getLogRegionname())
            .append("logCountry", getLogCountry())
            .append("logOs", getLogOs())
            .append("logBroswer", getLogBroswer())
            .append("logDate", getLogDate())
            .toString();
    }
}
