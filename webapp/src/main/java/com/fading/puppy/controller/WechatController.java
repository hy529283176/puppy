package com.fading.puppy.controller;

import com.fading.puppy.tools.ServletContextUtil;
import com.fading.puppy.tools.Sign;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


@RequestMapping("/wechatWebService")
@Controller
public class WechatController {

    @RequestMapping("/getWechatConfig")
    @ResponseBody
    public String getWXConfig(String url) {
        JSONObject jsonObject = null;
        try {
            String jsapiTicket = (String) ServletContextUtil.get().getAttribute("jsApiTicket");
            Map<String, String> res = Sign.sign(jsapiTicket, url);
            res.put("appId",(String) ServletContextUtil.get().getAttribute("appId"));
            jsonObject = JSONObject.fromObject(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
}
