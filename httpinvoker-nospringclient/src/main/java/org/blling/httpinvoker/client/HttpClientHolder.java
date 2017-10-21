package org.blling.httpinvoker.client;

import org.apache.http.client.HttpClient;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.util.HashMap;
import java.util.Map;

/**
* Add your comments here
* @author blling.fa@foxmail.com
* 2017-09-13
**/
public class HttpClientHolder {
    // HttpClient cache map
    private final Map<String, HttpClient> httpInvokerHttpClientCache = new HashMap<>();

    public HttpClient hcInstance(HttpInvokerConfig httpInvokerConfig) {
        HttpClient hcInstance = httpInvokerHttpClientCache.get(httpInvokerConfig.key());
        if(null == hcInstance) {
            hcInstance = initHcInstance(httpInvokerConfig);
        }
        return hcInstance;
    }

    private synchronized HttpClient initHcInstance(HttpInvokerConfig httpInvokerConfig) {
        HttpClient httpClient = httpInvokerHttpClientCache.get(httpInvokerConfig.key());
        if(null != httpClient) {
            return httpClient;
        }

        Registry<ConnectionSocketFactory> schemeRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", SSLConnectionSocketFactory.getSocketFactory())
                .build();

        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(schemeRegistry);
        // Max total connections
        connectionManager.setMaxTotal(httpInvokerConfig.maxTotal());
        // Max connections per host
        connectionManager.setDefaultMaxPerRoute(httpInvokerConfig.maxPerRoute());

        httpClient = HttpClientBuilder.create().setConnectionManager(connectionManager).build();
        httpInvokerHttpClientCache.put(httpInvokerConfig.key(), httpClient);
        return httpInvokerHttpClientCache.get(httpInvokerConfig.key());
    }
}
