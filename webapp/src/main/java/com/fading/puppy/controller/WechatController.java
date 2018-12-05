package com.fading.puppy.controller;

import com.fading.puppy.tools.AccessTokenOrJsapiTicketUtil;
import com.fading.puppy.tools.SignUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;


@RequestMapping("/wechatWebService")
@Controller
public class WechatController {

    @RequestMapping("/index")
    public ModelAndView index() {
        return new ModelAndView("wechat/index") ;
    }

    @RequestMapping("/getWechatConfig")
    @ResponseBody
    public String getWXConfig(String url) {
        JSONObject jsonObject = null;
        try {
            String jsapiTicket = AccessTokenOrJsapiTicketUtil.getJsapiTicket();
            Map<String, String> res = SignUtil.sign(jsapiTicket, url);
            res.put("appId", AccessTokenOrJsapiTicketUtil.getAppId());
            jsonObject = JSONObject.fromObject(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

}
