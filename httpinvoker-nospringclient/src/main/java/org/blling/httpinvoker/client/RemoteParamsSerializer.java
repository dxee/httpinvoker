package org.blling.httpinvoker.client;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Field;

/**
* Add your comments here
* @author blling.fa@foxmail.com
* 2017-09-18
**/
public class RemoteParamsSerializer extends ObjectOutputStream {
    private static final Logger LOGGER = LoggerFactory.getLogger(RemoteParamsSerializer.class);
    RemoteParamsSerializer(OutputStream out) throws IOException {
        super(out);
    }

    protected void writeClassDescriptor(ObjectStreamClass desc) throws IOException {
        if (RemoteInvocation.class.getName().equalsIgnoreCase(desc.getName())) {
            try {
                Field f = desc.getClass().getDeclaredField("name");
                f.setAccessible(true);
                f.set(desc, "org.springframework.remoting.support.RemoteInvocation");
            } catch (NoSuchFieldException | IllegalAccessException e) {
                LOGGER.error("RemoteParamsSerializer.writeClassDescriptor error: {}, will not write desc"
                        , e.getMessage()
                        , e);
            }
        }
        super.writeClassDescriptor(desc);
    }
}