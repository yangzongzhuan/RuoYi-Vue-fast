package com.ruoyi.common.utils.gallery;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class ImageUtils {

    /**
     * 利用随机数和时间戳生成文件名
     * @param file 文件
     * @return 最终文件名
     */
    public static String generateFileName(MultipartFile file) {
        // 文件名
        String fileName = file.getOriginalFilename();
        // 获取原始文件的后缀名
        String suffix = null;
        if (fileName != null) {
            suffix = getSuffix(fileName);
        }
        // 获取当前时间戳
        long timestamp = System.currentTimeMillis();
        // 生成随机六位字符串
        String randomString = generateRandomString(6);
        // 构造最终文件名
        fileName = randomString + "_" + timestamp + suffix;
        // 返回最终文件名称
        return fileName;
    }

    /**
     * 将MultipartFile类型转化为File类型，并保存到本地
     *
     * @param multipartFile 前端传入文件
     * @param directory     保存的文件夹
     * @param fileName      文件名称
     * @return file类型文件
     * @throws IOException 报错处理
     */
    public static File multipartFileToFile(MultipartFile multipartFile, String directory, String fileName) throws IOException {
        // 确定文件保存的目录路径
        Path directoryPath = Paths.get(directory);
        if (!Files.exists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }

        // 构造文件的完整路径
        Path filePath = Paths.get(directory, fileName);

        // 将 MultipartFile 内容写入到文件中
        File file = filePath.toFile();
        FileCopyUtils.copy(multipartFile.getBytes(), file);

        return file;
    }

    /**
     * 获取文件后缀名的方法
     *
     * @param fileName 文件名
     * @return 文件后缀
     */
    public static String getSuffix(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex != -1) {
            return fileName.substring(dotIndex);
        }
        // 如果文件没有后缀名，则返回一个默认的后缀名，比如 ".png" 或者 ".dat"
        return ".png";
    }

    /**
     * 生成随机字符串
     *
     * @param length 长度
     * @return 随机字符串
     */
    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }

    // 将字节数转换为合适的单位（KB、MB 等）
    public static String humanReadableByteCountBin(long bytes) {
        if (bytes < 1024) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(1024));
        String pre = "KMGTPE".charAt(exp - 1) + "";
        return String.format("%.1f %sB", bytes / Math.pow(1024, exp), pre);
    }

    /**
     * 删除本地文件
     *
     * @param localFilePath 文件路径
     */
    public static void deleteLocalFile(String localFilePath) {
        File file = new File(localFilePath);
        if (file.exists()) {
            file.delete();
        }
    }
}
