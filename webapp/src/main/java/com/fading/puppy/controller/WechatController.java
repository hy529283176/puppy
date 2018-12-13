package com.fading.puppy.controller;

import com.fading.puppy.tools.AccessTokenOrJsapiTicketUtil;
import com.fading.puppy.tools.ServletContextUtil;
import com.fading.puppy.tools.SignUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


@RequestMapping("/wechatWebService")
@Controller
public class WechatController {

    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView mov = new ModelAndView("wechat/index");
        mov.addObject("appId", AccessTokenOrJsapiTicketUtil.getAppId());
        return mov;
    }

    @RequestMapping(value = "/getWechatConfig")
    @ResponseBody
    public String getWXConfig(String url) {
        try {
            String jsapiTicket = AccessTokenOrJsapiTicketUtil.getJsapiTicket();
            Map<String, String> res = SignUtil.sign(jsapiTicket, url);
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

    @RequestMapping(value = "/getOpenId")
    @ResponseBody
    public String getOpenId(String code) {
        String openId = AccessTokenOrJsapiTicketUtil.getOpenId(code);
        Map<String, String> map = new HashMap<>();
        map.put("openId", openId);
        JSONObject jsonObject = JSONObject.fromObject(map);
        return jsonObject.toString();
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
