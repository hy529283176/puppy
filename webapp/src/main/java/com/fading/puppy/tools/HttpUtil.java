package com.fading.puppy.tools;

import net.sf.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 后端请求
 */
public class HttpUtil {

    /**
     * get请求
     */
    public static JSONObject doRequestByGet(String requestUrl){
        try {
            URL url = new URL(requestUrl.trim());
            //打开连接
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            if(200 == urlConnection.getResponseCode()){
                //获取返回的字符
                InputStream inputStream = urlConnection.getInputStream();
                JSONObject jsonObject = getJsonObjectMessages(inputStream);
                return jsonObject;
            }
        }  catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * post请求，带json参数
     */
    public static JSONObject doRequestByPost(String serviceUrl, JSONObject jsonObjectParams) {
        InputStream inputStream = null;
        OutputStreamWriter writer = null;
        try{
            URL url = new URL(serviceUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            connection.connect();

            //发送参数
            writer = new OutputStreamWriter(connection.getOutputStream(),"UTF-8");
            writer.write(jsonObjectParams.toString());
            //清理当前编辑器的左右缓冲区，并使缓冲区数据写入基础流
            writer.flush();

            //获取返回的字符
            inputStream = connection.getInputStream();
            JSONObject jsonObject = getJsonObjectMessages(inputStream);
            inputStream.close();
            return jsonObject;
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (writer != null) {
                try {
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * @Description: http请求发送xml内容
     * @param @param urlStr
     * @param @param xmlInfo
     * @param @return
     * @author dapengniao
     * @date 2016年3月10日 下午3:58:32
     */
    public static String sendXmlPost(String urlStr, String xmlInfo) {
        // xmlInfo xml具体字符串

        try {
            URL url = new URL(urlStr);
            URLConnection con = url.openConnection();
            con.setDoOutput(true);
            con.setRequestProperty("Pragma:", "no-cache");
            con.setRequestProperty("Cache-Control", "no-cache");
            con.setRequestProperty("Content-Type", "text/xml");
            OutputStreamWriter out = new OutputStreamWriter(
                    con.getOutputStream());
            out.write(new String(xmlInfo.getBytes("utf-8")));
            out.flush();
            out.close();
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String lines = "";
            for (String line = br.readLine(); line != null; line = br
                    .readLine()) {
                lines = lines + line;
            }
            return lines; // 返回请求结果
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "fail";
    }

    /**
     * 把请求返回的信息转为JSONObject
     * @param inputStream
     * @return
     * @throws Exception
     */
    public static JSONObject getJsonObjectMessages(InputStream inputStream) throws Exception{
        int size =inputStream.available();
        byte[] bs =new byte[size];
        inputStream.read(bs);
        String message=new String(bs,"UTF-8");
        inputStream.close();
        JSONObject jsonObject = JSONObject.fromObject(message);
        return jsonObject;
    }

}
