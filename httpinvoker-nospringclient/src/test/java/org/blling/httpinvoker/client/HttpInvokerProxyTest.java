package org.blling.httpinvoker.client;

import org.blling.httpinvoker.ClientInvokerConfig;
import org.blling.httpinvoker.server.service.IAnimalService;
import org.blling.httpinvoker.server.service.IUserService;
import org.junit.Assert;
import org.junit.Test;

/**
* Add your comments here
* @author blling.fa@foxmail.com
* 2017-09-17
**/
public class HttpInvokerProxyTest { 

    @Test
    public void testInvoke() throws Exception {
        ClientInvokerConfig clientInvokerConfig = new ClientInvokerConfig();

        // HttpInvokerExcutor
        HttpInvokerExecutor httpInvokerExecutor = new SimpleHttpInvokerExecutor(new HttpClientHolder());

        // HttpInvokerProxyHolder
        HttpInvokerProxyHolder httpInvokerProxyHolder = new HttpInvokerProxyHolder();

        // AnamalHttpInvokerConfig
        HttpInvokerConfig anamalHttpInvokerConfig = clientInvokerConfig.anamalHttpInvokerConfig();
        IAnimalService animalService = httpInvokerProxyHolder.proxyHttpInvoker(anamalHttpInvokerConfig
                , httpInvokerExecutor
                , IAnimalService.class);
        Assert.assertEquals(animalService.getAnimal(1L).getName(), "JAMES");

        // UserHttpInvokerConfig
        HttpInvokerConfig userHttpInvokerConfig = clientInvokerConfig.userHttpInvokerConfig();
        IUserService userService = httpInvokerProxyHolder.proxyHttpInvoker(userHttpInvokerConfig
                , httpInvokerExecutor
                , IUserService.class);
        Assert.assertEquals(userService.getUserById(2L).getName(), "JJ");
    }
        
}
