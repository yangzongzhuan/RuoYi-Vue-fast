package com.ruoyi.project.ftp.config;

import lombok.Data;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@EnableConfigurationProperties
@PropertySource(value = {"ftp.properties"})
@ConfigurationProperties(prefix = "ftp.client")
@Configuration
@Data
public class FTPPoolConfig extends GenericObjectPoolConfig {
    // 默认进入的路径
    String workingDirectory;
    // 主机地址
    String host;
    // 主机端口
    int port;
    // 主机用户名
    String username;
    // 主机密码
    String password;
    // 传输编码
    String encoding;
    // 被动模式：在这种模式下，数据连接是由客户程序发起的
    boolean passiveMode;
    // 连接超时时间
    int clientTimeout;
    // 线程数
    int threaNum;
    // 0=ASCII_FILE_TYPE(ASCII格式)，1=EBCDIC_FILE_TYPE，2=LOCAL_FILE_TYPE(二进制文件)
    int transferFileType;
    // 是否重命名
    boolean renameUploaded;
    // 重新连接时间
    int retryTimes;
    // 缓存大小
    int bufferSize;
    // 最大数
    int maxTotal;
    // 最小空闲
    int minldle;
    // 最大空闲
    int maxldle;
    // 最大等待时间
    int maxWait;
    // 池对象耗尽之后是否阻塞，maxWait < 0 时一直等待
    boolean blockWhenExhausted;
    // 取对象时验证
    boolean testOnBorrow;
    // 回收验证
    boolean testOnReturn;
    // 创建时验证
    boolean testOnCreate;
    // 空闲验证
    boolean testWhileldle;
    // 后进先出
    boolean lifo;
}
