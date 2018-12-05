package com.fading.puppy.tools;

import com.fading.puppy.enumpackage.WechatConfigEnum;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import java.io.*;
import java.util.Properties;

public class AccessTokenOrJsapiTicketUtil {

    private static Logger log = LoggerFactory.getLogger(AccessTokenOrJsapiTicketUtil.class);
    private static String appId = null;
    private static String appSecret = null;
    private static ServletContext sc = ServletContextUtil.get();

    /**
     * 初始化获取appId，appSecret,可以用spring注入方式，加载spring时，生成bean,
     * 就可以配置、方法都能调用了。
     */
    static  {
        Properties prop = new Properties();
        InputStream in = null;
        try {
            in =  AccessTokenOrJsapiTicketUtil.class.getClassLoader().getResourceAsStream("wechat.properties");
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

    public AccessTokenOrJsapiTicketUtil() {

    }

    public static void initAndSetAccessToken() {
        log.info("execute initAndSetAccessToken Start : {}", System.currentTimeMillis());

        String accessToken = createAccessToken(appId, appSecret);
        if(null != accessToken) {
            /**
            * cache access_token
            */
            sc.removeAttribute(WechatConfigEnum.ACCESSTOKEN.getName());
            sc.setAttribute(WechatConfigEnum.ACCESSTOKEN.getName(), accessToken);
            System.out.println("accessToken="+sc.getAttribute(WechatConfigEnum.ACCESSTOKEN.getName()).toString());
            System.out.println("=====================================");

            /**
             * cache jsapi_ticket
            */
            String jsApiTicket = createJsapiTicket(accessToken);
            if(null != jsApiTicket) {
                sc.removeAttribute(WechatConfigEnum.JSAPITICKE.getName());
                sc.setAttribute(WechatConfigEnum.JSAPITICKE.getName(), jsApiTicket);
                System.out.println("ticket="+sc.getAttribute(WechatConfigEnum.JSAPITICKE.getName()).toString());
                System.out.println("=====================================");

            }
            createMenus(accessToken);
        } else {
            log.info("execute initAndSetAccessToken accessToken 为空,获取失败.{}");
        }
        log.info("execute initAndSetAccessToken End   : {}", System.currentTimeMillis());
    }


    private static String createAccessToken(String appid, String appSecret) {
        String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
                + appid + "&secret=" + appSecret;
        System.out.println("URL for getting accessToken accessTokenUrl="+accessTokenUrl);

        JSONObject jsonObject = HttpUtil.doRequestByGet(accessTokenUrl);
        String accessToken = jsonObject.getString("access_token");
        String expires_in = jsonObject.getString("expires_in");
        System.out.println("accessToken="+accessToken);
        System.out.println("expires_in="+expires_in);
        System.out.println("======================================");
        return accessToken;
    }

    private static String createJsapiTicket(String accessToken) {
        String jsapiTicketUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+accessToken+"&type=jsapi";

        System.out.println("URL for getting ticket ticketUrl="+jsapiTicketUrl);

        JSONObject jsonObject = HttpUtil.doRequestByGet(jsapiTicketUrl);
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
     * 创建菜单
     * @param accessToken
     */
    private static void createMenus(String accessToken) {
        System.out.println("创建微信菜单....");
        if (accessToken  == null || accessToken.isEmpty()) {
            System.out.println("创建失败！");
        } else {
            try {
                String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+accessToken;
                InputStream inputStream = AccessTokenOrJsapiTicketUtil.class.getClassLoader().getResourceAsStream("menus.json");
                JSONObject jsonMenus = HttpUtil.getJsonObjectMessages(inputStream);
                JSONObject jsonObject = HttpUtil.doRequestByPost(url,jsonMenus);
                String errcode = jsonObject.getString("errcode");
                String errmsg = jsonObject.getString("errmsg");
                System.out.println(errcode);
                System.out.println(errmsg);
                if ("0".equals(errcode)) {
                    System.out.println("创建成功！");
                } else {
                    System.out.println("创建失败！");
                }
                System.out.println("======================================");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取缓存的access token
     * @return
     */
    public static String getAccessToken() {
        String accessToken = (String) ServletContextUtil.get().getAttribute(WechatConfigEnum.ACCESSTOKEN.getName());
        return accessToken;
    }

    public static String getAppId(){
        String appId = (String) ServletContextUtil.get().getAttribute(WechatConfigEnum.APPID.getName());
        return appId;
    }

    public static String getJsapiTicket(){
        String jsapiTicket = (String) ServletContextUtil.get().getAttribute(WechatConfigEnum.JSAPITICKE.getName());
        return jsapiTicket;
    }

}
