package com.fading.puppy.tools;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;

public class MultipartFileToFileUtil {
    /**
     * 把MultipartFile类型文件转换为一般的File类型文件
     * @param multipartFile MultipartFile类型文件参数
     * @return 返回File类型文件对象
     */
    public static File multipartFileToFile(String filePath,MultipartFile multipartFile) throws IOException {

        if(null != multipartFile || !multipartFile.isEmpty()){
            //在根目录下创建一个tempfileDir的临时文件夹
            File f = new File(filePath);
            if(!f.exists()){
                f.mkdirs();
            }
            //在tempfileDir的临时文件夹下创建一个新的和上传文件名一样的文件
            String filename = multipartFile.getOriginalFilename();
            String filepath = filePath+"/"+filename;
            File newFile = new File(filepath);

            //将MultipartFile文件转换，即写入File新文件中，返回File文件
            CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile) multipartFile;

        }
        return null;
    }
}
