package com.ruoyi.project.gallery.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 列表管理对象 gallery_image
 * 
 * @author ogcfun
 * @date 2024-02-28
 */
public class GalleryImage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 图片id */
    private Long fileId;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 上传用户 */
    @Excel(name = "上传用户")
    private String userName;

    /** 上传者头像 */
    @Excel(name = "上传者头像")
    private String userAvatar;

    /** 图片名称 */
    @Excel(name = "图片名称")
    private String fileName;

    /** 图片分类 */
    @Excel(name = "图片分类")
    private String fileType;

    /** 图片后缀 */
    @Excel(name = "图片后缀")
    private String fileSuffix;

    /** 图片地址 */
    @Excel(name = "图片地址")
    private String filePath;

    /** 图片压缩地址 */
    @Excel(name = "图片压缩地址")
    private String  fileCompressPath;

    /** 图片来源 */
    @Excel(name = "图片来源")
    private String fileOrigin;

    /** 图片宽度 */
    @Excel(name = "图片宽度")
    private String fileWidth;

    /** 图片高度 */
    @Excel(name = "图片高度")
    private String fileHeight;

    /** 图片大小 */
    @Excel(name = "图片大小")
    private String fileSize;

    /** 图片总点赞数 */
    @Excel(name = "图片总点赞数")
    private Long fileLikeCount;

    /** 图片总收藏数 */
    @Excel(name = "图片总收藏数")
    private Long fileCollectCount;

    /** 图片下载总次数 */
    @Excel(name = "图片下载总次数")
    private Long fileDownCount;

    /** 图片上传日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "图片上传日期", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date fileDate;

    /** 小程序懒加载图片 */
    private Integer fileShow;

    /** 图片审核 1是通过 0是不通过 */
    @Excel(name = "图片审核 1是通过 0是不通过")
    private Integer fileReview;

    /** 图片状态 1是禁用 0是正常 */
    @Excel(name = "图片状态 1是禁用 0是正常")
    private Integer fileStatus;

    /** 图片是否删除 1是删除 0是未删除 */
    @Excel(name = "图片是否删除 1是删除 0是未删除")
    private Integer fileDelete;

    public void setFileId(Long fileId) 
    {
        this.fileId = fileId;
    }

    public Long getFileId() 
    {
        return fileId;
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
    public void setUserAvatar(String userAvatar) 
    {
        this.userAvatar = userAvatar;
    }

    public String getUserAvatar() 
    {
        return userAvatar;
    }
    public void setFileName(String fileName) 
    {
        this.fileName = fileName;
    }

    public String getFileName() 
    {
        return fileName;
    }
    public void setFileType(String fileType) 
    {
        this.fileType = fileType;
    }

    public String getFileType() 
    {
        return fileType;
    }
    public void setFileSuffix(String fileSuffix) 
    {
        this.fileSuffix = fileSuffix;
    }

    public String getFileSuffix() 
    {
        return fileSuffix;
    }
    public void setFilePath(String filePath) 
    {
        this.filePath = filePath;
    }

    public String getFilePath() 
    {
        return filePath;
    }
    public void setFileOrigin(String fileOrigin) 
    {
        this.fileOrigin = fileOrigin;
    }

    public String getFileCompressPath() {
        return fileCompressPath;
    }

    public void setFileCompressPath(String fileCompressPath) {
        this.fileCompressPath = fileCompressPath;
    }

    public String getFileOrigin() 
    {
        return fileOrigin;
    }
    public void setFileWidth(String fileWidth) 
    {
        this.fileWidth = fileWidth;
    }

    public String getFileWidth() 
    {
        return fileWidth;
    }
    public void setFileHeight(String fileHeight) 
    {
        this.fileHeight = fileHeight;
    }

    public String getFileHeight() 
    {
        return fileHeight;
    }
    public void setFileSize(String fileSize) 
    {
        this.fileSize = fileSize;
    }

    public String getFileSize() 
    {
        return fileSize;
    }
    public void setFileLikeCount(Long fileLikeCount) 
    {
        this.fileLikeCount = fileLikeCount;
    }

    public Long getFileLikeCount() 
    {
        return fileLikeCount;
    }
    public void setFileCollectCount(Long fileCollectCount) 
    {
        this.fileCollectCount = fileCollectCount;
    }

    public Long getFileCollectCount() 
    {
        return fileCollectCount;
    }
    public void setFileDownCount(Long fileDownCount) 
    {
        this.fileDownCount = fileDownCount;
    }

    public Long getFileDownCount() 
    {
        return fileDownCount;
    }
    public void setFileDate(Date fileDate) 
    {
        this.fileDate = fileDate;
    }

    public Date getFileDate() 
    {
        return fileDate;
    }
    public void setFileShow(Integer fileShow) 
    {
        this.fileShow = fileShow;
    }

    public Integer getFileShow() 
    {
        return fileShow;
    }
    public void setFileReview(Integer fileReview) 
    {
        this.fileReview = fileReview;
    }

    public Integer getFileReview() 
    {
        return fileReview;
    }
    public void setFileStatus(Integer fileStatus) 
    {
        this.fileStatus = fileStatus;
    }

    public Integer getFileStatus() 
    {
        return fileStatus;
    }
    public void setFileDelete(Integer fileDelete) 
    {
        this.fileDelete = fileDelete;
    }

    public Integer getFileDelete() 
    {
        return fileDelete;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("fileId", getFileId())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("userAvatar", getUserAvatar())
            .append("fileName", getFileName())
            .append("fileType", getFileType())
            .append("fileSuffix", getFileSuffix())
            .append("filePath", getFilePath())
            .append("fileCompressPath",getFileCompressPath())
            .append("fileOrigin", getFileOrigin())
            .append("fileWidth", getFileWidth())
            .append("fileHeight", getFileHeight())
            .append("fileSize", getFileSize())
            .append("fileLikeCount", getFileLikeCount())
            .append("fileCollectCount", getFileCollectCount())
            .append("fileDownCount", getFileDownCount())
            .append("fileDate", getFileDate())
            .append("fileShow", getFileShow())
            .append("fileReview", getFileReview())
            .append("fileStatus", getFileStatus())
            .append("fileDelete", getFileDelete())
            .toString();
    }
}
