package org.blling.httpinvoker.client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
* Add your comments here
* @author blling@
* 2017-09-12
**/
public class HttpInvokerProxy implements InvocationHandler {
    private final HttpInvokerConfig httpInvokerConfig;
    private final HttpInvokerExecutor httpInvokerExecutor;
    private final Class<?> interfaces;

    public HttpInvokerProxy(
            HttpInvokerConfig httpInvokerConfig
            , HttpInvokerExecutor httpInvokerExecutor
            , Class<?> interfaces) {
        this.interfaces=interfaces;
        this.httpInvokerExecutor = httpInvokerExecutor;
        this.httpInvokerConfig = httpInvokerConfig;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // Do not proxy toString methods, see HttpInvokerClientInterceptor for more details
        if(isToStringMethod(method)) {
            return "HTTP httpinvoker proxy for service URL [" + this.httpInvokerConfig.serviceUrl() + "]";
        }

        RemoteInvocation invocation = new RemoteInvocation(method, args);
        RemoteInvocationResult result = httpInvokerExecutor.executeRequest(this.httpInvokerConfig, invocation);
        return result.checkAndReturn();
    }

    public boolean isToStringMethod(Method method) {
        return method != null && method.getName().equals("toString") && method.getParameterTypes().length == 0;
    }
}