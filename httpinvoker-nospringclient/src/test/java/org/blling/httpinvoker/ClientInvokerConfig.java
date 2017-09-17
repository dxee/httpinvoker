package org.blling.httpinvoker;

import org.blling.httpinvoker.client.HttpInvokerConfig;
import org.blling.httpinvoker.client.HttpInvokerExecutor;
import org.blling.httpinvoker.client.HttpInvokerProxy;

import org.blling.httpinvoker.client.*;
import org.blling.httpinvoker.server.service.IAnimalService;
import org.blling.httpinvoker.server.service.IUserService;

public class ClientInvokerConfig {

    public IUserService userService(HttpInvokerProxy httpInvokerProxy) {
        return httpInvokerProxy.proxyHttpInvoker(IUserService.class);
    }

    public IAnimalService animalService(HttpInvokerProxy httpInvokerProxy) {
        return httpInvokerProxy.proxyHttpInvoker(IAnimalService.class);
    }

    public HttpInvokerProxy userServiceHttpInvokerProxy(HttpInvokerConfig httpInvokerConfig
            , HttpInvokerExecutor httpInvokerExecutor) {
        return new HttpInvokerProxy(httpInvokerConfig, httpInvokerExecutor);
    }

    public HttpInvokerProxy animalHttpInvokerProxy(HttpInvokerConfig httpInvokerConfig
            , HttpInvokerExecutor httpInvokerExecutor) {
        return new HttpInvokerProxy(httpInvokerConfig, httpInvokerExecutor);
    }

    public HttpInvokerConfig anamalHttpInvokerConfig(){
        return new HttpInvokerConfig() {
            @Override
            public String key() {
                return "animal";
            }

            @Override
            public String serviceUrl() {
                return "http://localhost:9999/remote/animalService";
            }

            @Override
            public int connectTimeout() {
                return 10000;
            }

            @Override
            public int socketTimeout() {
                return 10000;
            }

            @Override
            public int maxTotal() {
                return 32;
            }

            @Override
            public int maxPerRoute() {
                return 4;
            }
        };
    }

    public HttpInvokerConfig userHttpInvokerConfig(){
        return new HttpInvokerConfig() {
            @Override
            public String key() {
                return "user";
            }

            @Override
            public String serviceUrl() {
                return "http://localhost:9999/remote/userService";
            }

            @Override
            public int connectTimeout() {
                return 10000;
            }

            @Override
            public int socketTimeout() {
                return 10000;
            }

            @Override
            public int maxTotal() {
                return 32;
            }

            @Override
            public int maxPerRoute() {
                return 4;
            }
        };
    }
}

