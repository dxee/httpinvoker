package org.blling.httpinvoker.client;

/**
* Add your comments here
* @author blling@
* 2017-09-12
**/
public interface HttpInvokerConfig {
    /**
     * The unique key for this HttpInvokerConfig
     * @return
     */
    String key();
    String url();
    int connectTimeout();
    int socketTimeout();
    int maxTotal();
    int maxPerRoute();
}
