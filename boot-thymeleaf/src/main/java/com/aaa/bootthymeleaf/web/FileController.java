package com.aaa.bootthymeleaf.web;

/**
 * 文件上传
 * @author liuzhen.tian
 * @version 1.0 FileController.java  2020/10/31 22:54
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

@Controller
public class  FileController {

    private String filePath;

    @GetMapping(value = "/file")
    public String file() {
        return "fileUpload";
    }

    @GetMapping(value = "/fileMany")
    public String fileUploadMany() {
        return "fileUploadMany";
    }

    /**
     * 单文件上传
     */
    @PostMapping(value = "/fileUpload")
    public String fileUpload(@RequestParam(value = "file") MultipartFile file, Model model, HttpServletRequest request) {
        if (file.isEmpty()) {
            System.out.println("文件为空");
        }
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        String filePath = "D://temp-rainy//"; // 上传后的路径
        fileName = UUID.randomUUID() + suffixName; // 新文件名
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filename = "/temp-rainy/" + fileName;
        model.addAttribute("filename", filename);
        return "fileUpload";
    }

    /**
     * 多文件上传
     */
    @PostMapping(value = "/fileUploadMany")
    @ResponseBody
    public List fileUploadMany(@RequestParam("uploadFiles")  MultipartFile[] multipartFile) {
        if (multipartFile.length ==0) {
            System.out.println("文件为空");
        }
        List<Map<String,Object>> root=new ArrayList<>();
        for (MultipartFile file : multipartFile) {
            Map<String,Object> result=new HashMap();//一个文件上传的结果
            String fileName = file.getOriginalFilename();  // 文件名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
            String filePath = "D://temp-rainy//"; // 上传后的路径
            fileName = UUID.randomUUID() + suffixName; // 新文件名
            File dest = new File(filePath + fileName);
            //判断文件父目录是否存在
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                //保存文件
                file.transferTo(dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String filename = "/temp-rainy/" + fileName;

            result.put("relativePath", filename);
            root.add(result);
        }
        return root;
    }

}

