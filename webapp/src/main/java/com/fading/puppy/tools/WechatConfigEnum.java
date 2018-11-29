package com.fading.puppy.tools;

public enum WechatConfigEnum {
    ACCESSTOKEN("accessToken"), JSAPITICKE("jsApiTicket"), APPID("appId"), APPSECRET("appSecret");

    private String name;

    private WechatConfigEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
