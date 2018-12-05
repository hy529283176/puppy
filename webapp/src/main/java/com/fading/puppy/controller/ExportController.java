package com.fading.puppy.controller;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.*;
import com.deepoove.poi.render.RenderAPI;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/export")
public class ExportController {

    @RequestMapping(value = "/exportWordByModel", method = RequestMethod.POST)
    public void exportWordService(@RequestParam Map<String, Object> params, HttpServletResponse response) {
        try {
            ServletOutputStream servletOutputStream = null;
            String mapStr = (String) params.get("params");
            JSONObject jsonObject = JSONObject.fromObject(mapStr);
            String templatePath =  jsonObject.getString("templatePath");
            String exportPath =  jsonObject.getString("exportPath");
            List<Object> datas = null;

            // 设置浏览器以下载的方式处理该文件名
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/msword");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            String name = simpleDateFormat.format(new Date());
            String fileName = "data_"+name+ ".docx";
            response.setHeader("Content-Disposition", "attachment;filename="
                    .concat(String.valueOf(URLEncoder.encode(fileName, "UTF-8"))));


            XWPFTemplate template = XWPFTemplate.compile(templatePath).render(datas);
            FileOutputStream out = new FileOutputStream(exportPath);
            template.write(out);

            servletOutputStream = response.getOutputStream();
            template.write(servletOutputStream);

            servletOutputStream.flush();
            servletOutputStream.close();
            out.flush();
            out.close();
            template.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
