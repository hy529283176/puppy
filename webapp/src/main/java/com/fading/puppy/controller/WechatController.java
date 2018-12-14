package com.fading.puppy.controller;

import com.fading.puppy.tools.AccessTokenOrJsapiTicketUtil;
import com.fading.puppy.tools.ServletContextUtil;
import com.fading.puppy.tools.SignUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


@RequestMapping("/wechatWebService")
@Controller
public class WechatController {

    @RequestMapping("/index")
    public ModelAndView index() {
        Map<String, String> messages = new HashMap<>();
        messages.put("appId", AccessTokenOrJsapiTicketUtil.getAppId());
        return new ModelAndView("wechat/index", messages);
    }

    @RequestMapping("/index1")
    public ModelAndView index1() {
        Map<String, String> messages = new HashMap<>();
        messages.put("appId", AccessTokenOrJsapiTicketUtil.getAppId());
        return new ModelAndView("wechat/index1", messages);
    }

    @RequestMapping("/index2")
    public ModelAndView index2() {
        Map<String, String> messages = new HashMap<>();
        messages.put("appId", AccessTokenOrJsapiTicketUtil.getAppId());
        return new ModelAndView("wechat/index2", messages);
    }

    @RequestMapping(value = "/getWechatConfig")
    @ResponseBody
    public String getWXConfig(String url) {
        try {
            String jsapiTicket = AccessTokenOrJsapiTicketUtil.getJsapiTicket();
            Map<String, String> res = SignUtil.sign(jsapiTicket, url);
            res.put("appId", AccessTokenOrJsapiTicketUtil.getAppId());
            JSONObject jsonObject = JSONObject.fromObject(res);
            return jsonObject.toString();
        } catch (Exception e) {
            Map map = new HashMap();
            map.put("errorMes",e.getMessage());
            map.put("errorMesDetails",e.getStackTrace());
            JSONObject jsonObject = JSONObject.fromObject(map);
            return jsonObject.toString();
        }
    }

    @RequestMapping(value = "/getOpenId", produces = "text/html;charset=UTF-8")
    public ModelAndView getOpenId(String url, HttpServletRequest request, HttpServletResponse response) throws Exception{
        String code = request.getParameter("code");
        System.out.println("code = " + code);
        if (code == null || "".equals(code)) {
            throw new RuntimeException("微信验证代码为空，用户拒绝");
        }

        JSONObject authInfo = AccessTokenOrJsapiTicketUtil.getOpenId(code);

        if (authInfo.size() == 2) {
            if ("40163".equals(authInfo.getString("errcode"))) {
                throw new RuntimeException("code已经被用过，重复调用");
            }
        }

        String openId = authInfo.getString("openid");

        url = encodeUrl(url);
        ModelAndView mv = new ModelAndView("redirect:" + url);

        Cookie cookie = new Cookie("openid", openId);
        cookie.setPath("/");
        cookie.setHttpOnly(false);
        response.addCookie(cookie);

        return mv;
    }



    private String encodeUrl(String url) throws Exception {
        if(url != null && url.indexOf("?") > 0) {
            String[] urlSplit = url.split("[?]");
            for (int i = urlSplit.length - 1; i > 0; i--) {
                int index = urlSplit[i].indexOf("=");
                if(index > 0) {
                    String keyName = urlSplit[i].substring(0, index + 1);
                    String value = urlSplit[i].substring(index + 1);
                    urlSplit[i - 1] = urlSplit[i - 1] + "?" + keyName + URLEncoder.encode(value, "utf-8");
                }
            }
            url = urlSplit[0];
        }
        return url;
    }

}
