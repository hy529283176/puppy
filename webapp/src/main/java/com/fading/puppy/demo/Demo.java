package com.fading.puppy.demo;

import com.fading.puppy.tools.AccessTokenOrJsapiTicketUtil;

public class Demo {

    public static void main(String[] args) {
        String accessToken = AccessTokenOrJsapiTicketUtil.getAccessToken();
        AccessTokenOrJsapiTicketUtil.createMenus(accessToken);
    }

}
