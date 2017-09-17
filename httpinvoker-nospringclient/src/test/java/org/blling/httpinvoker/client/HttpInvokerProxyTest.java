package test.org.blling.httpinvoker.client; 

import org.blling.httpinvoker.ClientInvokerConfig;
import org.blling.httpinvoker.client.*;
import org.blling.httpinvoker.server.service.IAnimalService;
import org.blling.httpinvoker.server.service.IUserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/**
* Add your comments here
* @author blling@
* 2017-09-17
**/
public class HttpInvokerProxyTest { 

    @Test
    public void testInvoke() throws Exception {
        ClientInvokerConfig clientInvokerConfig = new ClientInvokerConfig();
        // HttpInvokerConfig
        HttpInvokerConfig httpInvokerConfig = clientInvokerConfig.anamalHttpInvokerConfig();

        // HttpInvokerExcutor
        HttpInvokerExecutor httpInvokerExecutor = new SimpleHttpInvokerExecutor(new HttpClientHolder());

        // HttpInvokerProxy
        HttpInvokerProxy httpInvokerProxy = clientInvokerConfig.animalHttpInvokerProxy(
                clientInvokerConfig.anamalHttpInvokerConfig()
                , httpInvokerExecutor);

        IAnimalService animalService = clientInvokerConfig.animalService(httpInvokerProxy);
        Assert.assertEquals(animalService.getAnimal(1L).getName(), "JAMES");

        httpInvokerProxy = clientInvokerConfig.userServiceHttpInvokerProxy(
                clientInvokerConfig.userHttpInvokerConfig(),
                httpInvokerExecutor);
        IUserService userService = clientInvokerConfig.userService(httpInvokerProxy);
        Assert.assertEquals(userService.getUserById(2L).getName(), "JJ");
    }
        
}
