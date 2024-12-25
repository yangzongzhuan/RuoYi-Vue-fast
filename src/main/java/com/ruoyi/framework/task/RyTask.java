package com.ruoyi.framework.task;

import com.ruoyi.project.gallery.domain.GalleryTask;
import com.ruoyi.project.gallery.domain.GalleryUser;
import com.ruoyi.project.gallery.service.IGalleryTaskService;
import com.ruoyi.project.gallery.service.IGalleryUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.controller.BaseController;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 * 定时任务调度测试
 * 
 * @author ruoyi
 */
@Component("ryTask")
public class RyTask extends BaseController
{
    @Autowired
    private IGalleryTaskService galleryTaskService;

    @Autowired
    private IGalleryUserService galleryUserService;

    /**
     * 定时重置每日任务
     */
    public void resetGalleryTask() {
        // 获取任务对象
       GalleryTask galleryTask = new GalleryTask();
        // 查找galleryTask对象，并将对象的值存入list数组中
        List<GalleryTask> list = galleryTaskService.selectGalleryTaskList(galleryTask);

        // 获取今天的日期
        LocalDate today = LocalDate.now();
        // 将LocalDate转换为Date
        Date date = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());

        if (!list.isEmpty()) {
            // 使用增强for遍历list，依次修改值
            for (GalleryTask task : list) {
                task.setTaskLikeCount(0L);
                task.setTaskSignIn(0);
                task.setTaskAward(0);
                task.setTaskDate(date);
                // 更新任务对象
                galleryTaskService.updateGalleryTask(task);
            }
        }
    }

    /**
     * 定时更新用户下载上传次数
     */
    public void resetGalleryUser() {
        // 获取用户对象
        GalleryUser galleryUser = new GalleryUser();
        // 查找galleryUser对象，并将对象的值存入list数组中
        List<GalleryUser> list = galleryUserService.selectGalleryUserList(galleryUser);

        // 获取今天的日期
        LocalDate today = LocalDate.now();
        // 将LocalDate转换为Date
        Date date = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());

        if (!list.isEmpty()) {
            // 使用增强for遍历list，依次修改值
            for (GalleryUser user : list) {
                user.setUserDownCount(100L);
                user.setUserUploadCount(10L);
                // 更新用户对象
               galleryUserService.updateGalleryUser(user);
            }
        }
    }

    public void ryMultipleParams(String s, Boolean b, Long l, Double d, Integer i)
    {
        System.out.println(StringUtils.format("执行多参方法： 字符串类型{}，布尔类型{}，长整型{}，浮点型{}，整形{}", s, b, l, d, i));
    }

    public void ryParams(String params)
    {
        System.out.println("执行有参方法：" + params);
    }

    public void ryNoParams()
    {
        System.out.println("执行无参方法");
    }
}


