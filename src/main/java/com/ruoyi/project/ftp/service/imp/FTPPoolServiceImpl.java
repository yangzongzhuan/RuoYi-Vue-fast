package com.ruoyi.project.ftp.service.imp;

import com.ruoyi.project.ftp.config.FTPPoolConfig;
import com.ruoyi.project.ftp.factory.FTPClientFactory;
import com.ruoyi.project.ftp.service.FTPPoolService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class FTPPoolServiceImpl implements FTPPoolService {
    /**
     * ftp 连接池生成
     */
    private GenericObjectPool<FTPClient> pool;

    /**
     * ftp 客户端配置文件
     */
    @Autowired
    private FTPPoolConfig config;

    /**
     * ftp 客户端工厂
     */
    @Autowired
    private FTPClientFactory factory;

    /**
     * 初始化pool
     */
    @PostConstruct
    private void initPool() {
        this.pool = new GenericObjectPool<FTPClient>(this.factory, this.config);
    }
    /**
     * 获取ftpClient
     */
    @Override
    public FTPClient borrowObject() {
        if (this.pool != null) {
            try {
                return this.pool.borrowObject();
            } catch (Exception e) {
                log.error("获取 FTPClient 失败 ", e);
            }
        }
        return null;
    }

    /**
     * 归还 ftpClient
     */
    @Override
    public void returnObject(FTPClient ftpClient) {
        if (this.pool != null && ftpClient != null) {
            this.pool.returnObject(ftpClient);
        }
    }

    @Override
    public FTPPoolConfig getFtpPoolConfig() {
        return config;
    }
}
