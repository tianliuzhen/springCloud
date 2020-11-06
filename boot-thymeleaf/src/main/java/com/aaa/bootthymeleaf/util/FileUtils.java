package com.aaa.bootthymeleaf.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author liuzhen.tian
 * @version 1.0 FileUtils.java  2020/11/1 13:49
 */
public class FileUtils {

    /**
     * @param file     文件
     * @param path     文件存放路径
     * @param fileName 保存的文件名
     * @return
     */
    public static boolean upload(MultipartFile file, String path, String fileName) {

        //确定上传的文件名
        String realPath = path + "\\" + fileName;
        System.out.println("上传文件：" + realPath);

        File dest = new File(realPath);

        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }

        try {
            //保存文件
            file.transferTo(dest);
            return true;
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }

    }

}



