package com.fading.puppy.tools;
import java.io.BufferedOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExportTextUtil {
    /**
     * 声明日志记录器
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ExportTextUtil.class);

    /**
     * 导出文本文件
     * @param response
     * @param text
     */
    public static void writeToText(HttpServletResponse response,String text){//设置响应的字符集
        response.setCharacterEncoding("utf-8");
        //设置响应内容的类型
        response.setContentType("text/plain");
        //设置文件的名称和格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String name = simpleDateFormat.format(new Date());
        String fileName = "data_"+ name;
        response.setHeader("Content-Disposition","attachment; filename=" + fileName + ".txt");
        //根据 ], 回车换行
        StringBuffer write = new StringBuffer();
//        String enter = "],\r\n";
        BufferedOutputStream buff = null;
        ServletOutputStream outStr = null;
        //判断传来的文本是否为空，并去除开头结尾的空格
        String textNew = delNull(text);
        try {
            outStr = response.getOutputStream();
            buff = new BufferedOutputStream(outStr);
//            write.append("\"");
//            write.append(textNew.replaceAll("],", enter));
//            write.append("\"");
            write.append(textNew.toString());
            buff.write(write.toString().getBytes("UTF-8"));
            buff.flush();
            buff.close();
        } catch (Exception e) {
            LOGGER.error("导出文件文件出错，e:{}",e);
        } finally {try {
            buff.close();
            outStr.close();
        } catch (Exception e) {
            LOGGER.error("关闭流对象出错 e:{}",e);
        }
        }
    }

    /**
     * 如果字符串对象为 null，则返回空字符串，否则返回去掉字符串前后空格的字符串
     * @param str
     * @return
     */
    public static String delNull(String str) {
        String returnStr="";
        if (StringUtils.isNotBlank(str)) {
            returnStr=str.trim();
        }
        return returnStr;
    }

}
