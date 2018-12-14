package com.fading.puppy.enumpackage;


public enum WechatConfigEnum {
    ACCESSTOKEN("accessToken"), JSAPITICKE("jsApiTicket"), APPID("appId"), APPSECRET("appSecret"), TOKEN("token");

    private String name;

    private WechatConfigEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
