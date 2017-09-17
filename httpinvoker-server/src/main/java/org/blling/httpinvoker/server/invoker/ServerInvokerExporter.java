package org.blling.httpinvoker.server.invoker;

import org.blling.httpinvoker.server.service.IAnimalService;
import org.blling.httpinvoker.server.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

/**
 * Created by Janita on 2017-03-24 14:59
 */
@Configuration
public class ServerInvokerExporter {

    @Autowired(required = false)
    private IUserService userService;

    @Autowired(required = false)
    private IAnimalService animalService;

    /**
     * name = "/remote/userService" 客户的的服务的url就是：
     * IP:PORT/remote/userService
     * @return
     */
    @Bean(name = "/remote/userService")
    public HttpInvokerServiceExporter userService(){
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setService(userService);
        exporter.setServiceInterface(IUserService.class);

        return exporter;
    }

    /**
     * 调用url：http://localhost:9999/remote/animalService
     * @return
     */
    @Bean(name = "/remote/animalService")
    public HttpInvokerServiceExporter animalServic(){
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setService(animalService);
        exporter.setServiceInterface(IAnimalService.class);

        return exporter;
    }

    /**
     * springMVC中需要此配置
     * 但在此，上面的name = "/remote/userService"已经做了相同的事情
     */
//    @Bean
//    public SimpleUrlHandlerMapping handlerMapping(){
//        SimpleUrlHandlerMapping handlerMapping = new SimpleUrlHandlerMapping();
//        Properties properties = new Properties();
//        properties.put("/userService",httpInvokerServiceExporter());
//        handlerMapping.setMappings(properties);
//
//        return handlerMapping;
//    }
}
