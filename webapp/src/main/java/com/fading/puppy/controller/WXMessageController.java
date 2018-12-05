package com.fading.puppy.controller;

import com.fading.puppy.tools.CheckServiceUtil;
import com.fading.puppy.tools.MessageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;


/**
 * 消息处理接口，被动回复，关键词回复
 */
@Controller
@RequestMapping(value = "/wechat/messageService")
public class WXMessageController {

    /*
     * 自定义token, 用作生成签名,从而验证安全性
     * */
    private static final String TOKEN = "puppy";

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String checkService(HttpServletRequest request) {
        /**
         * 接收微信服务器发送请求时传递过来的参数
         */
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce"); //随机数
        String echostr = request.getParameter("echostr");//随机字符串

        /**
         * 将token、timestamp、nonce三个参数进行字典序排序
         * 并拼接为一个字符串
         */
        String sortStr = CheckServiceUtil.sort(TOKEN,timestamp,nonce);
        /**
         * 字符串进行shal加密
         */
        String mySignature = CheckServiceUtil.shal(sortStr);

        /**
         * 校验微信服务器传递过来的签名 和  加密后的字符串是否一致, 若一致则签名通过
         */
        if(!"".equals(signature) && !"".equals(mySignature) && signature.equals(mySignature)){
            System.out.println("-----签名校验通过-----");
            return echostr;
        }else {
            System.out.println("-----校验签名失败-----");
            return "";
        }

    }

    @RequestMapping(method = RequestMethod.POST)
    public void responseMessages(HttpServletRequest req, HttpServletResponse resp){
        // TODO 接收、处理、响应由微信服务器转发的用户发送给公众帐号的消息
        PrintWriter out = null;
        try {
            String result = "";
            // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
            req.setCharacterEncoding("UTF-8");
            resp.setCharacterEncoding("UTF-8");
            System.out.println("请求进入");

            //解析用户发送给微信的消息
            Map<String,String> map = MessageUtil.parseXml(req);

            System.out.println("开始构造消息");
            result = MessageUtil.buildXml(map);
            System.out.println(result);

            if("".equals(result)){
                result = "emmmmm, 我唔知你讲咩";
            }

            //响应消息
            out = resp.getWriter();
            out.print(result);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("发生异常："+ e.getMessage());
        } finally {
            if (null != out) {
                out.flush();
                out.close();
            }
        }
    }

}
