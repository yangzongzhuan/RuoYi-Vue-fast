package com.ruoyi.project.ftp.factory;

import com.ruoyi.project.ftp.config.FTPPoolConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * FtpClient 工厂声场连接对象
 */
@Component
@Slf4j
public class FTPClientFactory implements PooledObjectFactory<FTPClient> {
    /**
     * 注入 ftp 连接配置
     */
    @Autowired
    FTPPoolConfig config;

    /**
     * 创建连接到池中
     *
     * @return
     * @throws Exception
     */
    @Override
    public PooledObject<FTPClient> makeObject() throws Exception {
        FTPClient ftpClient = new FTPClient();
        ftpClient.setConnectTimeout(config.getClientTimeout());
        ftpClient.connect(config.getHost(), config.getPort());
        int reply = ftpClient.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftpClient.disconnect();
            return null;
        }
        boolean success;
        if (StringUtils.isBlank(config.getUsername())) {
            success = ftpClient.login("anonymous", "anonymous");
        } else {
            success = ftpClient.login(config.getUsername(), config.getPassword());
        }
        if (!success) {
            return null;
        }
        ftpClient.setFileType(config.getTransferFileType());
        ftpClient.setBufferSize(1024);
        ftpClient.setControlEncoding(config.getEncoding());
        if (config.isPassiveMode()) {
            ftpClient.enterLocalPassiveMode();
        }
        log.debug("创建ftp连接");
        return new DefaultPooledObject<>(ftpClient);
    }

    /**
     * 链接状态检查
     *
     * @param pool
     * @return
     */
    @Override
    public boolean validateObject(PooledObject<FTPClient> pool) {
        FTPClient ftpClient = pool.getObject();
        try {
            return ftpClient != null && ftpClient.sendNoOp();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 销毁连接，当连接池空闲数量达到上限时，调用此方法销毁连接
     *
     * @param pool
     * @throws Exception
     */
    @Override
    public void destroyObject(PooledObject<FTPClient> pool) throws Exception {
        FTPClient ftpClient = pool.getObject();
        if (ftpClient != null) {
            try {
                ftpClient.disconnect();
                log.debug("销毁ftp连接");
            } catch (Exception e) {
                log.error("销毁ftpClient异常，error：", e.getMessage());
            }
        }
    }

    /**
     * 钝化连接，是连接变为可用状态
     *
     * @param p
     * @throws Exception
     */
    @Override
    public void passivateObject(PooledObject<FTPClient> p) throws Exception{
        FTPClient ftpClient = p.getObject();
        try {
            ftpClient.changeWorkingDirectory(config.getWorkingDirectory());
            ftpClient.logout();
            if (ftpClient.isConnected()) {
                ftpClient.disconnect();
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not disconnect from server.", e);
        }
    }

    /**
     * 初始化连接
     *
     * @param pool
     * @throws Exception
     */
    @Override
    public void activateObject(PooledObject<FTPClient> pool) throws Exception {
        FTPClient ftpClient = pool.getObject();
        ftpClient.connect(config.getHost(),config.getPort());
        ftpClient.login(config.getUsername(), config.getPassword());
        ftpClient.setControlEncoding(config.getEncoding());
        ftpClient.changeWorkingDirectory(config.getWorkingDirectory());
        //设置上传文件类型为二进制，否则将无法打开文件
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
    }

    /**
     * 获取 FTP 连接配置
     * @return
     */
    public FTPPoolConfig getConfig(){
        return config;
    }
}
