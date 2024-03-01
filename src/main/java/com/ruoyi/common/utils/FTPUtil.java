package com.ruoyi.common.utils;

import cn.hutool.core.util.CharsetUtil;
import com.ruoyi.project.ftp.service.FTPPoolService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * FTP工具类
 *
 * @Scope设置Bean的作用域
 * 作用域类型            使用范围	            作用域描述
 * singleton	    所有Spring应用	    默认值,IoC容器只存在单例
 * prototype	    所有Spring应用	    每次从IoC容器中取出都是一个bean，都创建一个新的bean
 * session	        Spring web应用	    Http会话
 * application	    Spring web应用	    web工程生命周期
 * request	        Spring web应用	    web工程单次请求
 * globalSession	Spring web应用	    在一个全局的 httpSession中，一个bean对应一个实例。实践中基本不使用
 */
@Component
@Slf4j
public class FTPUtil {
    /**
     * ftp 连接池
     */
    @Autowired
    private FTPPoolService ftpPoolService;

    /**
     * 从FTP服务器上下载文件,支持断点续传，下载百分比汇报
     *
     * @param remote 远程文件路径及名称
     * @param local  本地文件完整绝对路径
     * @return 下载的状态
     * @throws IOException
     */
    public DownloadStatus download(String remote, String local) throws IOException {
        FTPClient ftpClient = ftpPoolService.borrowObject();
        // 设置被动模式,由于Linux安全性考虑，端口没有全部放开，所有被动模式不能用
        ftpClient.enterLocalPassiveMode();
        // 设置以二进制方式传输
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        DownloadStatus result;
        try {
            // 检查远程文件是否存在
            FTPFile[] files = ftpClient.listFiles(new String(remote.getBytes(CharsetUtil.UTF_8), CharsetUtil.ISO_8859_1));
            if (files.length != 1) {
                log.info("远程文件不存在");
                return DownloadStatus.RemoteFileNotExist;
            }
            long lRemoteSize = files[0].getSize();
            File f = new File(local);
            // 本地存在文件，进行断点下载
            if (f.exists()) {
                long localSize = f.length();
                // 判断本地文件大小是否大于远程文件大小
                if (localSize >= lRemoteSize) {
                    log.info("本地文件大于远程文件，下载中止");
                    return DownloadStatus.LocalFileBiggerThanRemoteFile;
                }
                // 进行断点续传，并记录状态
                FileOutputStream out = new FileOutputStream(f, true);
                ftpClient.setRestartOffset(localSize);
                InputStream in = ftpClient.retrieveFileStream(new String(remote.getBytes(CharsetUtil.UTF_8), CharsetUtil.ISO_8859_1));
                byte[] bytes = new byte[1024];
                long step = lRemoteSize / 100;
                step = step == 0 ? 1 : step;// 文件过小，step可能为0
                long process = localSize / step;
                int c;
                while ((c = in.read(bytes)) != -1) {
                    out.write(bytes, 0, c);
                    localSize += c;
                    long nowProcess = localSize / step;
                    if (nowProcess > process) {
                        process = nowProcess;
                        if (process % 10 == 0) {
                            log.info("下载进度：" + process);
                        }
                    }
                }
                in.close();
                out.close();
                boolean isDo = ftpClient.completePendingCommand();
                if (isDo) {
                    result = DownloadStatus.DownloadFromBreakSuccess;
                } else {
                    result = DownloadStatus.DownloadFromBreakFailed;
                }
            } else {
                OutputStream out = new FileOutputStream(f);
                InputStream in = ftpClient.retrieveFileStream(new String(remote.getBytes(CharsetUtil.UTF_8), CharsetUtil.ISO_8859_1));
                byte[] bytes = new byte[1024];
                long step = lRemoteSize / 100;
                step = step == 0 ? 1 : step;// 文件过小，step可能为0
                long process = 0;
                long localSize = 0L;
                int c;
                while ((c = in.read(bytes)) != -1) {
                    out.write(bytes, 0, c);
                    localSize += c;
                    long nowProcess = localSize / step;
                    if (nowProcess > process) {
                        process = nowProcess;
                        if (process % 10 == 0) {
                            log.info("下载进度：" + process);
                        }
                    }
                }
                in.close();
                out.close();
                boolean upNewStatus = ftpClient.completePendingCommand();
                if (upNewStatus) {
                    result = DownloadStatus.DownloadNewSuccess;
                } else {
                    result = DownloadStatus.DownloadNewFailed;
                }
            }
        } catch (Exception e) {
            log.error("download error", e);
        } finally {
            ftpPoolService.returnObject(ftpClient);
        }
        return DownloadStatus.DownloadNewFailed;
    }

    /**
     * 切换远程目录
     *
     * @param path
     * @return
     * @throws IOException
     */
    public boolean changeDirectory(String path) {
        FTPClient ftpClient = ftpPoolService.borrowObject();
        boolean result = false;
        try {
            result = ftpClient.changeWorkingDirectory(path);
        } catch (Exception e) {
            log.error("changeDirectory error ", e);
        } finally {
            ftpPoolService.returnObject(ftpClient);
        }
        return result;
    }

    /**
     * 创建远程目录
     *
     * @param pathName
     * @return
     * @throws IOException
     */
    public boolean createDirectory(String pathName) {
        FTPClient ftpClient = ftpPoolService.borrowObject();
        boolean result = false;
        try {
            result = ftpClient.makeDirectory(pathName);
        } catch (Exception e) {
            log.error("createDirectory error ", e);
        } finally {
            ftpPoolService.returnObject(ftpClient);
        }
        return result;
    }

    /**
     * 删除远程目录
     *
     * @param path
     * @return
     * @throws IOException
     */
    public boolean removeDirectory(String path) {
        FTPClient ftpClient = ftpPoolService.borrowObject();
        boolean result = false;
        try {
            result = ftpClient.removeDirectory(path);
        } catch (Exception e) {
            log.error("removeDirectory error ", e);
        } finally {
            ftpPoolService.returnObject(ftpClient);
        }
        return result;
    }

    /**
     * 删除远程目录，可递归删除所有
     *
     * @param path
     * @param isAll
     * @return
     * @throws IOException
     */
    public boolean removeDirectory(String path, boolean isAll) {
        FTPClient ftpClient = ftpPoolService.borrowObject();
        boolean result = false;
        try {
            if (!isAll) {
                return removeDirectory(path);
            }
            FTPFile[] ftpFileArr = ftpClient.listFiles(path);
            if (ftpFileArr == null || ftpFileArr.length == 0) {
                return removeDirectory(path);
            }
            //
            for (FTPFile ftpFile : ftpFileArr) {
                String name = ftpFile.getName();
                if (ftpFile.isDirectory()) {
                    log.info("* [sD]Delete subPath [" + path + "/" + name + "]");
                    if (!ftpFile.getName().equals(".") && (!ftpFile.getName().equals(".."))) {
                        removeDirectory(path + "/" + name, true);
                    }
                } else if (ftpFile.isFile()) {
                    log.info("* [sF]Delete file [" + path + "/" + name + "]");
                    deleteFile(path + "/" + name);
                } else if (ftpFile.isSymbolicLink()) {
                } else if (ftpFile.isUnknown()) {
                }
            }
            result = ftpClient.removeDirectory(path);
        } catch (Exception e) {
            log.error("removeDirectory error ", e);
        } finally {
            ftpPoolService.returnObject(ftpClient);
        }
        return result;
    }

    /**
     * 查看目录是否存在
     *
     * @param path
     * @return
     * @throws IOException
     */

    public boolean isDirectoryExists(String path) {
        FTPClient ftpClient = ftpPoolService.borrowObject();
        boolean flag = false;
        try {
            FTPFile[] ftpFileArr = ftpClient.listFiles(path);
            for (FTPFile ftpFile : ftpFileArr) {
                if (ftpFile.isDirectory() && ftpFile.getName().equalsIgnoreCase(path)) {
                    flag = true;
                    break;
                }
            }
        } catch (Exception e) {
            log.error("isDirectoryExists error ", e);
        } finally {
            ftpPoolService.returnObject(ftpClient);
        }
        return flag;
    }

    /**
     * 得到某个目录下的文件名列表
     *
     * @param path
     * @return
     * @throws IOException
     */

    public List<String> getFileList(String path) {
        FTPClient ftpClient = ftpPoolService.borrowObject();
        List<String> retList = new ArrayList<String>();
        try {
            FTPFile[] ftpFiles = ftpClient.listFiles(path);
            if (ftpFiles == null || ftpFiles.length == 0) {
                return retList;
            }
            for (FTPFile ftpFile : ftpFiles) {
                if (ftpFile.isFile()) {
                    retList.add(ftpFile.getName());
                }
            }
        } catch (Exception e) {
            log.error("getFileList error ", e);
        } finally {
            ftpPoolService.returnObject(ftpClient);
        }
        return retList;
    }

    /**
     * 删除文件
     *
     * @param pathName
     * @return
     * @throws IOException
     */
    public boolean deleteFile(String pathName) {
        FTPClient ftpClient = ftpPoolService.borrowObject();
        boolean result = false;
        try {
            result = ftpClient.deleteFile(pathName);
        } catch (Exception e) {
            log.error("deleteFile error ", e);
        } finally {
            ftpPoolService.returnObject(ftpClient);
        }
        return result;
    }

    /**
     * 上传文件到FTP服务器，支持断点续传
     *
     * @param local  本地文件名称，绝对路径
     * @param remote 远程文件路径，按照Linux上的路径指定方式，支持多级目录嵌套，支持递归创建不存在的目录结构
     * @return 上传结果
     * @throws IOException
     */


    public UploadStatus upload(String local, String remote) {
        ftpPoolService = BeanUtils.getBean(FTPPoolService.class);
        FTPClient ftpClient = ftpPoolService.borrowObject();
        UploadStatus result = UploadStatus.UploadNewFileFailed;
        try {
            ftpClient.changeWorkingDirectory(ftpPoolService.getFtpPoolConfig().getWorkingDirectory());
            // 设置PassiveMode传输
            ftpClient.enterLocalPassiveMode();
            // 设置以二进制流的方式传输
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.setControlEncoding(CharsetUtil.UTF_8);
            // 对远程目录的处理
            String remoteFileName = remote;
            if (remote.contains("/")) {
                remoteFileName = remote.substring(remote.lastIndexOf("/") + 1);
                // 创建服务器远程目录结构，创建失败直接返回
                if (createDirecroty(remote, ftpClient) == UploadStatus.CreateDirectoryFail) {
                    return UploadStatus.CreateDirectoryFail;
                }
            }
            // 检查远程是否存在文件
            FTPFile[] files = ftpClient.listFiles(new String(remoteFileName.getBytes(CharsetUtil.UTF_8), CharsetUtil.UTF_8));
            if (files.length == 1) {
                long remoteSize = files[0].getSize();
                File f = new File(local);
                long localSize = f.length();
                if (remoteSize == localSize) { // 文件存在
                    return UploadStatus.FileExits;
                } else if (remoteSize > localSize) {
                    return UploadStatus.RemoteFileBiggerThanLocalFile;
                }
                // 尝试移动文件内读取指针,实现断点续传
                result = uploadFile(remoteFileName, f, ftpClient, remoteSize);
                // 如果断点续传没有成功，则删除服务器上文件，重新上传
                if (result == UploadStatus.UploadFromBreakFailed) {
                    if (!ftpClient.deleteFile(remoteFileName)) {
                        return UploadStatus.DeleteRemoteFaild;
                    }
                    result = uploadFile(remoteFileName, f, ftpClient, 0);
                }
            } else {
                result = uploadFile(remoteFileName, new File(local), ftpClient, 0);
            }
        } catch (Exception e) {
            log.error("upload error ", e);
        } finally {
            ftpPoolService.returnObject(ftpClient);
        }

        return result;
    }

    /**
     * 递归创建远程服务器目录
     *
     * @param remote    远程服务器文件绝对路径
     * @param ftpClient FTPClient对象
     * @return 目录创建是否成功
     * @throws IOException
     */

    public UploadStatus createDirecroty(String remote, FTPClient ftpClient) throws IOException {
        UploadStatus status = UploadStatus.CreateDirectorySuccess;
        String directory = remote.substring(0, remote.lastIndexOf("/") + 1);
        if (!directory.equalsIgnoreCase("/") && !ftpClient.changeWorkingDirectory(new String(directory.getBytes(CharsetUtil.UTF_8), CharsetUtil.UTF_8))) {
            // 如果远程目录不存在，则递归创建远程服务器目录
            int start = 0;
            int end = 0;
            if (directory.startsWith("/")) {
                start = 1;
            } else {
                start = 0;
            }
            end = directory.indexOf("/", start);
            while (true) {
                String subDirectory = new String(remote.substring(start, end).getBytes(CharsetUtil.UTF_8), CharsetUtil.UTF_8);
                if (!ftpClient.changeWorkingDirectory(subDirectory)) {
                    if (ftpClient.makeDirectory(subDirectory)) {
                        ftpClient.changeWorkingDirectory(subDirectory);
                    } else {
                        log.info("创建目录失败");
                        return UploadStatus.CreateDirectoryFail;
                    }
                }
                start = end + 1;
                end = directory.indexOf("/", start);
                // 检查所有目录是否创建完毕
                if (end <= start) {
                    break;
                }
            }
        }
        return status;
    }

    /**
     * 上传文件到服务器,新上传和断点续传
     *
     * @param remoteFile 远程文件名，在上传之前已经将服务器工作目录做了改变，一定要注意这里的 remoteFile 已经别被编码 ISO-8859-1
     * @param localFile  本地文件File句柄，绝对路径
     * @param ftpClient  FTPClient引用
     * @return
     * @throws IOException
     */

    public UploadStatus uploadFile(String remoteFile, File localFile, FTPClient ftpClient, long remoteSize) {
        if (null == ftpClient) {
            ftpClient = ftpPoolService.borrowObject();
        }
        if (null == ftpClient) {
            return null;
        }
        UploadStatus status = UploadStatus.UploadNewFileFailed;

        try (RandomAccessFile raf = new RandomAccessFile(localFile, "r");
             // 一定要注意这里的 remoteFile 已经别被编码 ISO-8859-1
             OutputStream out = ftpClient.appendFileStream(remoteFile);) {
            // 显示进度的上传
            log.info("localFile.length():" + localFile.length());
            long step = localFile.length() / 100;
            step = step == 0 ? 1 : step;// 文件过小，step可能为0
            long process = 0;
            long localreadbytes = 0L;

            // 断点续传
            if (remoteSize > 0) {
                ftpClient.setRestartOffset(remoteSize);
                process = remoteSize / step;
                raf.seek(remoteSize);
                localreadbytes = remoteSize;
            }
            byte[] bytes = new byte[1024];
            int c;
            while ((c = raf.read(bytes)) != -1) {
                out.write(bytes, 0, c);
                localreadbytes += c;
                if (localreadbytes / step != process) {
                    process = localreadbytes / step;
                    if (process % 10 == 0) {
                        log.info("上传进度：" + process);
                    }
                }
            }
            out.flush();
            raf.close();
            out.close();
            // FTPUtil的upload方法在执行ftpClient.completePendingCommand()之前应该先关闭OutputStream，否则主线程会在这里卡死执行不下去。
            // 原因是completePendingCommand()会一直在等FTP Server返回226 Transfer complete，但是FTP Server只有在接受到OutputStream执行close方法时，才会返回。
            boolean result = ftpClient.completePendingCommand();
            if (remoteSize > 0) {
                status = result ? UploadStatus.UploadFromBreakSuccess : UploadStatus.UploadFromBreakFailed;
            } else {
                status = result ? UploadStatus.UploadNewFileSuccess : UploadStatus.UploadNewFileFailed;
            }
        } catch (Exception e) {
            log.error("uploadFile error ", e);
        }
        return status;
    }

    /**
     * 下载资源文件
     *
     * @param sourceFileName
     * @return
     * @throws IOException
     */
    public InputStream downFile(String sourceFileName) {
        FTPClient ftpClient = ftpPoolService.borrowObject();
        InputStream result = null;
        try {
            result = ftpClient.retrieveFileStream(sourceFileName);
        } catch (Exception e) {
            log.error("deleteFile error ", e);
        } finally {
            ftpPoolService.returnObject(ftpClient);
        }
        return result;
    }

    /**
     * 查看用户目录
     *
     * @param path
     * @throws IOException
     */
    public void listFilesDir(String path) {
        FTPClient ftpClient = ftpPoolService.borrowObject();
        try {
            String ftpPath = path;
            ftpClient.changeWorkingDirectory(ftpPath);
            FTPFile[] files = ftpClient.listFiles();
            for (FTPFile ff : files) {
                if (!ff.isDirectory()) {
                } else {
                    if (!ff.getName().startsWith(".")) {
                        ftpPath = ff.getName() + "/";
                        ftpClient.changeWorkingDirectory(ftpPath);
                        listFilesDir(ftpPath);
                        ftpClient.changeWorkingDirectory(path);
                    }
                }
            }
        } catch (Exception e) {
            log.error("deleteFile error ", e);
        } finally {
            ftpPoolService.returnObject(ftpClient);
        }
    }


    /**
     * 上传状态枚举
     */
    public enum UploadStatus {
        CreateDirectoryFail, // 远程服务器相应目录创建失败
        CreateDirectorySuccess, // 远程服务器闯将目录成功
        UploadNewFileSuccess, // 上传新文件成功
        UploadNewFileFailed, // 上传新文件失败
        FileExits, // 文件已经存在
        RemoteFileBiggerThanLocalFile, // 远程文件大于本地文件
        UploadFromBreakSuccess, // 断点续传成功
        UploadFromBreakFailed, // 断点续传失败
        DeleteRemoteFaild; // 删除远程文件失败
    }

    /**
     * 下载状态枚举
     */
    public enum DownloadStatus {
        RemoteFileNotExist, // 远程文件不存在
        DownloadNewSuccess, // 下载文件成功
        DownloadNewFailed, // 下载文件失败
        LocalFileBiggerThanRemoteFile, // 本地文件大于远程文件
        DownloadFromBreakSuccess, // 断点续传成功
        DownloadFromBreakFailed; // 断点续传失败
    }
}
