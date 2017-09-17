package org.blling.httpinvoker.client;

import org.springframework.remoting.support.RemoteInvocation;
import org.springframework.remoting.support.RemoteInvocationResult;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
* Add your comments here
* @author blling@
* 2017-09-12
**/
public class HttpInvokerProxy implements InvocationHandler {
    private final HttpInvokerConfig httpInvokerConfig;
    private final HttpInvokerExecutor httpInvokerExecutor;

    public HttpInvokerProxy(HttpInvokerConfig httpInvokerConfig
            , HttpInvokerExecutor httpInvokerExecutor) {
        this.httpInvokerExecutor = httpInvokerExecutor;
        this.httpInvokerConfig = httpInvokerConfig;
    }

    public <T> T proxyHttpInvoker(Class<T> iClazz) {
        return (T) Proxy.newProxyInstance(
                HttpInvokerProxy.class.getClassLoader(),
                new Class[] { iClazz },
                this
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // Do not proxy toString methods, see HttpInvokerClientInterceptor for more details
        if(isToStringMethod(method)) {
            return "HTTP httpinvoker proxy for service URL [" + this.httpInvokerConfig.serviceUrl() + "]";
        }

        RemoteInvocationResult result = null;
        try {
            RemoteInvocation invocation = new RemoteInvocation(method, args);
            result = httpInvokerExecutor.executeRequest(this.httpInvokerConfig, invocation);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return result.checkAndReturn();
    }

    public boolean isToStringMethod(Method method) {
        return method != null && method.getName().equals("toString") && method.getParameterTypes().length == 0;
    }
}