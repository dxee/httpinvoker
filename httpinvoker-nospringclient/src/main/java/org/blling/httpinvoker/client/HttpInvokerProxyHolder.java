package org.blling.httpinvoker.client;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
* Add your comments here
* @author blling.fa@foxmail.com
* 2017-09-18
**/
public class HttpInvokerProxyHolder {
    // HttpClient cache map
    private final Map<String, Object> httpInvokerHttpClientCache = new HashMap<>();
    
    /**
    * Add your comments here
    * 2017-09-18
    **/
    public <T> T proxyHttpInvoker(HttpInvokerConfig httpInvokerConfig
            , HttpInvokerExecutor httpInvokerExecutor
            , Class<T> interfaces) {
        T clazzInstance = (T)httpInvokerHttpClientCache.get(proxyCacheKey(httpInvokerConfig,
                interfaces)
        );

        if(null == clazzInstance) {
            clazzInstance = initProxyHttpInvoker(httpInvokerConfig, httpInvokerExecutor, interfaces);
        }

        return clazzInstance;
    }

    /**
    * Add your comments here
    * 2017-09-18
    **/
    private synchronized <T> T initProxyHttpInvoker(HttpInvokerConfig httpInvokerConfig
            , HttpInvokerExecutor httpInvokerExecutor
            , Class<T> interfaces) {

        T clazzInstance = (T)httpInvokerHttpClientCache.get(proxyCacheKey(httpInvokerConfig,
                interfaces));

        if(null != clazzInstance) {
            return clazzInstance;
        }
        
        return (T) Proxy.newProxyInstance(HttpInvokerProxy.class.getClassLoader(),
                new Class[] { interfaces },
                new HttpInvokerProxy(httpInvokerConfig, httpInvokerExecutor, interfaces)
        );
    }

    /**
    * Add your comments here
    * 2017-09-18
    **/
    private String proxyCacheKey(HttpInvokerConfig httpInvokerConfig
            , Class<?> interfaces) {
        return httpInvokerConfig.key() + "#" + interfaces.getName();
    }
}