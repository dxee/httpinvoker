package org.springframework.remoting.support;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

/**
* Add your comments here
* @author blling@
* 2017-09-11
**/
public class RemoteInvocationResult implements Serializable {

    /** Use serialVersionUID from Spring 1.1 for interoperability */
    private static final long serialVersionUID = 2138555143707773549L;


    private Object value;

    private Throwable exception;


    public RemoteInvocationResult(Object value) {
        this.value = value;
    }

    public RemoteInvocationResult(Throwable exception) {
        this.exception = exception;
    }

    public RemoteInvocationResult() {
    }


    public void setValue(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return this.value;
    }

    public void setException(Throwable exception) {
        this.exception = exception;
    }

    public Throwable getException() {
        return this.exception;
    }

    public boolean hasException() {
        return (this.exception != null);
    }

    public boolean hasInvocationTargetException() {
        return (this.exception instanceof InvocationTargetException);
    }


    public Object checkAndReturn() throws Throwable {
        // If exception is null return the value
        if (this.exception == null) {
            return this.value;
        }

        // If exception nonull then throw it
        Throwable exToThrow = this.exception;
        if (this.exception instanceof InvocationTargetException) {
            exToThrow = ((InvocationTargetException) this.exception).getTargetException();
        }
        fillInClientStackTraceIfPossible(exToThrow);
        throw exToThrow;
    }

    public void fillInClientStackTraceIfPossible(Throwable ex) {
        if (ex == null) {
            return;
        }
        StackTraceElement[] clientStack = new Throwable().getStackTrace();
        Set<Throwable> visitedExceptions = new HashSet<Throwable>();
        Throwable exToUpdate = ex;
        while (exToUpdate != null && !visitedExceptions.contains(exToUpdate)) {
            StackTraceElement[] serverStack = exToUpdate.getStackTrace();
            StackTraceElement[] combinedStack = new StackTraceElement[serverStack.length + clientStack.length];
            System.arraycopy(serverStack, 0, combinedStack, 0, serverStack.length);
            System.arraycopy(clientStack, 0, combinedStack, serverStack.length, clientStack.length);
            exToUpdate.setStackTrace(combinedStack);
            visitedExceptions.add(exToUpdate);
            exToUpdate = exToUpdate.getCause();
        }
    }
}
