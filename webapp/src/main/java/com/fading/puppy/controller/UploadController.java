package com.fading.puppy.controller;

import com.fading.puppy.tools.Base64Util;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
@Controller
@RequestMapping(value = "/uploadSystem")
public class UploadController {

    @RequestMapping(value = "/conmonFilesUpload")
    public String uploadFile(@RequestParam MultipartFile[] files, HttpServletRequest request) throws IOException {
        String path = "E:\\Intellij Project\\puppy\\webapp\\src\\main\\webapp\\upload";
        String  mes = "error";
        //判断file数组不能为空并且长度大于0
        if (files != null && files.length > 0) {
            //循环获取file数组中得文件
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                System.out.println(Base64Util.fileToBase64(file.getInputStream()));
                String str =  Base64Util.fileToBase64(file.getInputStream());
                String newFile = Base64Util.base64ToFile(str,path,"zongdi.shp");
                System.out.println(newFile);
            }
            mes = "ok";
        }
        request.setAttribute("mes",mes);
        return "test";
    }
}
