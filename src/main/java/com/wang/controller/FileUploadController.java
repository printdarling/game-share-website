package com.wang.controller;

import com.wang.entity.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class FileUploadController {

    @Value("${upload.path}")
    private String uploadPath;

    @PostMapping("/single")
    public Result uploadFile(@RequestParam("file") MultipartFile file) {
        System.out.println(file.getOriginalFilename());
        if (file.isEmpty()) {
            return new Result(false,555,"上传文件为空",null);
        }

        try {
            String fileName = StringUtils.cleanPath(new File(file.getOriginalFilename()).getName());
            File dest = new File(uploadPath + File.separator + fileName);
            file.transferTo(dest);

            String fileDownloadUri = "/assets/" + fileName;
            return new Result(true,222,"成功",fileDownloadUri);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false,555,"文件上传失败",null);
        }
    }
}
