package org.blling.httpinvoker.client;

/**
* Add your comments here
* @author blling.fan@foxmail.com
* 2017-09-12
**/
public interface HttpInvokerExecutor {
    RemoteInvocationResult executeRequest(HttpInvokerConfig config, RemoteInvocation invocation)
            throws Exception;
}
