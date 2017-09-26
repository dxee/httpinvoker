package org.blling.httpinvoker.client.invoker;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.blling.httpinvoker.server.service.IAnimalService;
import org.blling.httpinvoker.server.service.IUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpComponentsHttpInvokerRequestExecutor;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

/**
 * Created by Janita on 2017-03-24 15:52
 */
@Configuration
public class ClientInvokerConfig {

    @Bean
    public HttpComponentsHttpInvokerRequestExecutor httpComponentsHttpInvokerRequestExecutor(){
        HttpComponentsHttpInvokerRequestExecutor bean = new HttpComponentsHttpInvokerRequestExecutor();
        PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();
        manager.setMaxTotal(100);

        HttpClient httpClient = HttpClientBuilder.create().build();
        bean.setHttpClient(httpClient);
        return bean;
    }

    @Bean
    public HttpInvokerProxyFactoryBean userService(){
        HttpInvokerProxyFactoryBean bean = new HttpInvokerProxyFactoryBean();
        //此路径是由方法提供项目中暴露的bean的name决定的（@Bean(name = "/remote/userService")）
        bean.setServiceUrl("http://localhost:9999/remote/userService");
        bean.setServiceInterface(IUserService.class);
        bean.setHttpInvokerRequestExecutor(httpComponentsHttpInvokerRequestExecutor());

        return bean;
    }

    @Bean
    public HttpInvokerProxyFactoryBean animalService(){
        HttpInvokerProxyFactoryBean bean = new HttpInvokerProxyFactoryBean();
        bean.setServiceUrl("http://localhost:9999/remote/animalService");
        bean.setServiceInterface(IAnimalService.class);
        bean.setHttpInvokerRequestExecutor(httpComponentsHttpInvokerRequestExecutor());

        return bean;
    }

}
