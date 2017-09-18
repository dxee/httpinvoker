package org.blling.httpinvoker.client;


import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

/**
* Add your comments here
* @author blling@
* 2017-09-18
**/
public class RemoteResultDeserializer extends ObjectInputStream {
    RemoteResultDeserializer(InputStream is) throws IOException {
        super(is);
    }

    protected Class<?> resolveClass(ObjectStreamClass cl) throws ClassNotFoundException, IOException{
        String name = cl.getName();
        if(name.equalsIgnoreCase("org.springframework.remoting.support.RemoteInvocationResult")) {
            return RemoteInvocationResult.class;
        }

        return super.resolveClass(cl);
    }
}