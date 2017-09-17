**Usecase**  
Call spring httpinvoker services without import the dependencies of spring.  

**Background**  
I use guice + jersey for my daily work, i must call the services exported by spring httpinvoker exporter. 
I searched a lot and do not find any httpinvoker client repo that could call the spring httpinvoker services without the spring dependencies.
So this repo is born.  

**Steps**  
1. start the `httpinvoker-server`  
```shell
cd httpinvoker-server  
mvn spring-boot:run
```
2. run `httpinvoker-nospringclient` test
```shell
cd httpinvoker-nospringclient  
mvn test
```  

**Demo**  
Demo code is in the test class of `HttpInvokerProxyTest`

**Thanks**  
Thanks for this [repo](https://github.com/janforp/boot-httpinvoker-parent) decrease a lot of test work for me.