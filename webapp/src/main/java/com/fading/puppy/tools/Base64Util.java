package com.fading.puppy.tools;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Base64Util {

    /**
     * 将内容转化为base64字符串
     * @param filepath 路径
     * @param filename 文件名
     * @return 返回一存有文件名和内容的map
     * @throws IOException
     */
    public static String fileToBase64(String filepath, String filename) throws IOException {
        String fileName = filepath + filename; // 源文件
        String strBase64 = null;
        InputStream in = null;
        try {
            in = new FileInputStream(fileName);
            // in.available()返回文件的字节长度
            byte[] bytes = new byte[in.available()];
            // 将文件中的内容读入到数组中
            in.read(bytes);
            // strBase64 = new BASE64Encoder().encode(bytes); // 将字节流数组转换为字符串
            strBase64 = encode(bytes); // 将字节流数组转换为字符串
        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }finally{
            if(in!=null)
                in.close();
        }
        return strBase64;
    }

    public static String fileToBase64(String filepathame) throws IOException {
        String fileName = filepathame; // 源文件
        String strBase64 = null;
        try {
            InputStream in = new FileInputStream(fileName);
            // in.available()返回文件的字节长度
            byte[] bytes = new byte[in.available()];
            // 将文件中的内容读入到数组中
            in.read(bytes);
            // strBase64 = new BASE64Encoder().encode(bytes); // 将字节流数组转换为字符串
            strBase64 = encode(bytes); // 将字节流数组转换为字符串
            in.close();
        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return strBase64;
    }

    public static String fileToBase64(InputStream fileIn) throws IOException {
        String strBase64 = null;
        try {
            InputStream in = fileIn;
            // in.available()返回文件的字节长度
            byte[] bytes = new byte[in.available()];
            // 将文件中的内容读入到数组中
            in.read(bytes);
            // strBase64 = new BASE64Encoder().encode(bytes); // 将字节流数组转换为字符串
            strBase64 = encode(bytes); // 将字节流数组转换为字符串
            in.close();
        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return strBase64;
    }

    /**
     * base64流的字符串转化为文件
     * @param strBase64 base64字符串参数
     * @param filepath 文件保存路径
     * @param filename 文件保存名称
     * @return
     * @throws IOException
     */
    public static String base64ToFile(String strBase64, String filepath, String filename) throws IOException {
        String string = strBase64;
        File dir = new File(filepath);
        // 现在创建目录
        if(!dir.exists()){
            dir.mkdirs();
        }
        String fileName = filepath +"/"+ filename;// 生成的新文件
        ByteArrayInputStream in = null;
        FileOutputStream out = null;
        try {
            // 解码，然后将字节转换为文件
            // byte[] bytes = new BASE64Decoder().decodeBuffer(string);
            // byte[] bytes = decode(string.trim().replaceAll(" ", ""));
            byte[] bytes = decode(string.trim());
            // 将字符串转换为byte数组
            in = new ByteArrayInputStream(bytes);
            byte[] buffer = new byte[1024];
            out = new FileOutputStream(fileName);
            int bytesum = 0;
            int byteread = 0;
            while ((byteread = in.read(buffer)) != -1) {
                bytesum += byteread;
                out.write(buffer, 0, byteread); // 文件写操作
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }finally{
            if(out!=null)
                out.close();
            if(in!=null)
                in.close();
        }
        return fileName;
    }

    /**
     * 编码
     *
     * @param bstr
     * @return String
     */
    public static String encode(byte[] bstr) {
        return new BASE64Encoder().encode(bstr);
    }

    /**
     * 解码
     *
     * @param str
     * @return string
     */
    public static byte[] decode(String str) {
        byte[] bt = null;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            bt = decoder.decodeBuffer(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bt;
    }
}
