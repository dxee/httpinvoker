package org.blling.httpinvoker;

import org.blling.httpinvoker.client.HttpInvokerConfig;
import org.blling.httpinvoker.client.HttpInvokerExecutor;
import org.blling.httpinvoker.client.HttpInvokerProxy;

import org.blling.httpinvoker.client.*;
import org.blling.httpinvoker.server.service.IAnimalService;
import org.blling.httpinvoker.server.service.IUserService;

/**
* Add your comments here
* @author blling@
* 2017-09-18
**/
public class ClientInvokerConfig {
    
    public HttpInvokerConfig anamalHttpInvokerConfig(){
        return new HttpInvokerConfig() {
            @Override
            public String key() {
                return "animal";
            }

            @Override
            public String url() {
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
            public String url() {
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

