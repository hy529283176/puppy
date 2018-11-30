package com.fading.puppy.tools;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

public class AccessTokenOrJsapiTicketTool {

    private static Logger log = LoggerFactory.getLogger(AccessTokenOrJsapiTicketTool.class);
    private static String appId = null;
    private static String appSecret = null;
    private static ServletContext sc = ServletContextUtil.get();

    /**
     * 初始化获取appId，appSecret
     */
    static  {
        Properties prop = new Properties();
        InputStream in = null;
        try {
            in =  AccessTokenOrJsapiTicketTool.class.getClassLoader().getResourceAsStream("wechat.properties");
            prop.load(in);
            appId = prop.getProperty(WechatConfigEnum.APPID.getName());
            appSecret = prop.getProperty(WechatConfigEnum.APPSECRET.getName());
            sc.setAttribute(WechatConfigEnum.APPID.getName(), appId);
            in.close();
        } catch (IOException e) {
            log.info("execute initAndSetAccessToken {}", e.getMessage());
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void initAndSetAccessToken() throws Exception {
        log.info("execute initAndSetAccessToken Start : {}", System.currentTimeMillis());

        String accessToken = getAccessToken(appId, appSecret);
        if(null != accessToken) {
            /**
            * cache access_token
            */
            sc.removeAttribute(WechatConfigEnum.ACCESSTOKEN.getName());
            sc.setAttribute(WechatConfigEnum.ACCESSTOKEN.getName(), accessToken);
            System.out.println("新accessToken="+sc.getAttribute(WechatConfigEnum.ACCESSTOKEN.getName()).toString());
            System.out.println("=====================================");

            /**
             * cache jsapi_ticket
            */
            String jsApiTicket = getJsapiTicket(accessToken);
            if(null != jsApiTicket) {
                sc.removeAttribute(WechatConfigEnum.JSAPITICKE.getName());
                sc.setAttribute(WechatConfigEnum.JSAPITICKE.getName(), jsApiTicket);
                System.out.println("新ticket="+sc.getAttribute(WechatConfigEnum.JSAPITICKE.getName()).toString());
                System.out.println("=====================================");

            }
        } else {
            log.info("execute initAndSetAccessToken accessToken 为空,获取失败.{}");
        }
        log.info("execute initAndSetAccessToken End   : {}", System.currentTimeMillis());
    }




    public static String getAccessToken(String appid, String appSecret) throws Exception{
        String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
                + appid + "&secret=" + appSecret;
        System.out.println("URL for getting accessToken accessTokenUrl="+accessTokenUrl);

        JSONObject jsonObject = getWXTokenOrTicket(accessTokenUrl);
        String accessToken = jsonObject.getString("access_token");
        String expires_in = jsonObject.getString("expires_in");
        System.out.println("accessToken="+accessToken);
        System.out.println("expires_in="+expires_in);
        System.out.println("======================================");
        return accessToken;
    }

    public static String getJsapiTicket(String accessToken) throws Exception{
        String jsapiTicketUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+accessToken+"&type=jsapi";

        System.out.println("URL for getting accessToken accessTokenUrl="+jsapiTicketUrl);

        JSONObject jsonObject = getWXTokenOrTicket(jsapiTicketUrl);
        String jsapiTicket = jsonObject.getString("ticket");
        String errcode = jsonObject.getString("errcode");
        String errmsg = jsonObject.getString("errmsg");
        String expires_in = jsonObject.getString("expires_in");
        System.out.println("ticket="+jsapiTicket);
        System.out.println("errcode="+errcode);
        System.out.println("errmsg="+errmsg);
        System.out.println("expires_in="+expires_in);
        System.out.println("======================================");
        return jsapiTicket;
    }

    /**
     * 请求获取accessToken或jsapiTicket
     * @param wxServiceUrl 请求地址
     * @return 返回数据json对象
     * @throws Exception
     */
    public static JSONObject getWXTokenOrTicket(String wxServiceUrl) {
        InputStream inputStream = null;
        try{
            URL url = new URL(wxServiceUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.connect();
            //获取返回的字符
            inputStream = connection.getInputStream();
            int size =inputStream.available();
            byte[] bs =new byte[size];
            inputStream.read(bs);
            String message=new String(bs,"UTF-8");
            inputStream.close();
            //获取access_token
            JSONObject jsonObject = JSONObject.fromObject(message);
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
        }
        return null;
    }

}
