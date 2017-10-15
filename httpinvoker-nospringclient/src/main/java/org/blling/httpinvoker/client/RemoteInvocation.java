package org.blling.httpinvoker.client;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
* Add your comments here
* @author blling.fan@foxmail.com
* 2017-09-12
**/
public class RemoteInvocation implements Serializable {
    /** use serialVersionUID from Spring 1.1 for interoperability */
    private static final long serialVersionUID = 6876024250231820554L;

    private String methodName;

    private Class<?>[] parameterTypes;

    private Object[] arguments;

    private Map<String, Serializable> attributes;

    public RemoteInvocation(Method method, Object[] arguments) {
        this.methodName = method.getName();
        this.parameterTypes = method.getParameterTypes();
        this.arguments = arguments;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Class<?>[] getParameterTypes() {
        return this.parameterTypes;
    }

    public void setArguments(Object[] arguments) {
        this.arguments = arguments;
    }

    public Object[] getArguments() {
        return this.arguments;
    }


    public void addAttribute(String key, Serializable value) throws IllegalStateException {
        if (this.attributes == null) {
            this.attributes = new HashMap<String, Serializable>();
        }
        if (this.attributes.containsKey(key)) {
            throw new IllegalStateException("There is already an attribute with key '" + key + "' bound");
        }
        this.attributes.put(key, value);
    }

    public Serializable getAttribute(String key) {
        if (this.attributes == null) {
            return null;
        }
        return this.attributes.get(key);
    }

    public void setAttributes(Map<String, Serializable> attributes) {
        this.attributes = attributes;
    }

    public Map<String, Serializable> getAttributes() {
        return this.attributes;
    }


    public Object invoke(Object targetObject)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method method = targetObject.getClass().getMethod(this.methodName, this.parameterTypes);
        return method.invoke(targetObject, this.arguments);
    }


    public static String classNamesToString(Class... classes) {
        return classNamesToString((Collection) Arrays.asList(classes));
    }

    public static String classNamesToString(Collection<Class<?>> classes) {
        if (null == classes || classes.isEmpty()) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder("[");
            Iterator it = classes.iterator();

            while(it.hasNext()) {
                Class<?> clazz = (Class)it.next();
                sb.append(clazz.getName());
                if (it.hasNext()) {
                    sb.append(", ");
                }
            }

            sb.append("]");
            return sb.toString();
        }
    }

    @Override
    public String toString() {
        return "RemoteInvocation: method name '" + this.methodName + "'; parameter types " +
                classNamesToString(this.parameterTypes);
    }
}