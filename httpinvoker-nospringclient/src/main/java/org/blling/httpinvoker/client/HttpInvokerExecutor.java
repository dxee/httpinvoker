package org.blling.httpinvoker.client;

import org.springframework.remoting.support.RemoteInvocation;
import org.springframework.remoting.support.RemoteInvocationResult;

/**
* Add your comments here
* @author blling@
* 2017-09-12
**/
public interface HttpInvokerExecutor {
    RemoteInvocationResult executeRequest(HttpInvokerConfig config, RemoteInvocation invocation)
            throws Exception;
}
