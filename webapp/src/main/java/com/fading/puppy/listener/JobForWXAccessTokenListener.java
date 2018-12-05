package com.fading.puppy.listener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.fading.puppy.tools.AccessTokenOrJsapiTicketUtil;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;


/**
 * 定时刷新accessToken,jsapi_ticket
 */
public class JobForWXAccessTokenListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {

            Runnable runnable = new Runnable() {
                public void run() {
                    try {
                        /**
                         * 定时设置accessToken
                         */
                        AccessTokenOrJsapiTicketUtil.initAndSetAccessToken();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
            service.scheduleAtFixedRate(runnable, 1, 7170, TimeUnit.SECONDS);
        }

    }
}