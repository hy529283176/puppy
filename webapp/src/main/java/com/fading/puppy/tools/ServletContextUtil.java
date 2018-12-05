package com.fading.puppy.tools;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.ServletContext;

/**
 * 全局缓存accessToken，jsapi_ticket
 */
public class ServletContextUtil {

    private static ServletContext serveltContext = null;

    private ServletContextUtil(){};

    public synchronized static ServletContext get() {

        if(null == serveltContext) {
            WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
            serveltContext = webApplicationContext.getServletContext();
        }
        return serveltContext;
    }

}
