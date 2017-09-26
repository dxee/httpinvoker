package org.blling.httpinvoker.client;

import java.io.*;
import java.rmi.RemoteException;
import java.util.zip.GZIPInputStream;

import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;

/**
* Add your comments here
* @author blling@
* 2017-09-12
**/
public class SimpleHttpInvokerExecutor implements HttpInvokerExecutor {
    public static final String CONTENT_TYPE_SERIALIZED_OBJECT = "application/x-java-serialized-object";
    public static final String HTTP_HEADER_ACCEPT_LANGUAGE = "Accept-Language";
    public static final String HTTP_HEADER_ACCEPT_ENCODING = "Accept-Encoding";
    public static final String HTTP_HEADER_CONTENT_ENCODING = "Content-Encoding";
    public static final String ENCODING_GZIP = "gzip";
    public static final int SERIALIZED_INVOCATION_BYTE_ARRAY_INITIAL_SIZE = 1024;

    private final HttpClientHolder httpClientHolder;

    public SimpleHttpInvokerExecutor(HttpClientHolder httpClientHolder) {
        this.httpClientHolder = httpClientHolder;
    }

    @Override
    public final RemoteInvocationResult executeRequest(HttpInvokerConfig httpInvokerConfig, RemoteInvocation invocation) throws Exception {
        HttpPost httpPost = httpPost(httpInvokerConfig, invocation);
        try {
            // Execute post
            HttpResponse httpResponse = httpClientHolder.hcInstance(httpInvokerConfig).execute(httpPost);

            // Validate response
            validateResponse(httpInvokerConfig, httpResponse);

            // Export response
            InputStream responseBody;
            if (isGzipResponse(httpResponse)) {
                responseBody = new GZIPInputStream(httpResponse.getEntity().getContent());
            } else {
                responseBody = httpResponse.getEntity().getContent();
            }

            // Read result
            return readRemoteInvocationResult(responseBody);
        } finally {
            httpPost.releaseConnection();
        }
    }

    protected HttpEntity remoteInvocationEntity(RemoteInvocation invocation) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(SERIALIZED_INVOCATION_BYTE_ARRAY_INITIAL_SIZE);
        ObjectOutputStream oos = new RemoteParamsSerializer(baos);
        try {
            oos.writeObject(invocation);
            ByteArrayEntity byteArrayEntity = new ByteArrayEntity(baos.toByteArray());
            byteArrayEntity.setContentType(CONTENT_TYPE_SERIALIZED_OBJECT);
            return byteArrayEntity;
        } finally {
            oos.close();
        }
    }

    protected RemoteInvocationResult readRemoteInvocationResult(InputStream is)
            throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new RemoteResultDeserializer(is);
        try {
            Object obj = ois.readObject();
            if (obj instanceof RemoteInvocationResult) {
                return (RemoteInvocationResult) obj;
            }
            throw new RemoteException("Deserialized object needs to be assignable to type [" +
                    RemoteInvocationResult.class.getName() + "]: " + obj);
        } finally {
            ois.close();
        }
    }

    protected HttpPost httpPost(HttpInvokerConfig config, RemoteInvocation invocation) throws IOException {
        HttpPost httpPost = new HttpPost(config.url());

        // Request config
        RequestConfig.Builder builder = RequestConfig.custom();
        builder.setConnectTimeout(config.connectTimeout());
        builder.setSocketTimeout(config.socketTimeout());
        httpPost.setConfig(builder.build());

        // Request header
        httpPost.addHeader(HTTP_HEADER_ACCEPT_LANGUAGE, "en-US,en;q=0.5");
        httpPost.addHeader(HTTP_HEADER_ACCEPT_ENCODING, ENCODING_GZIP);

        // Request body
        HttpEntity entity = remoteInvocationEntity(invocation);
        httpPost.setEntity(entity);

        return httpPost;
    }

    protected void validateResponse(HttpInvokerConfig config, HttpResponse response)
            throws IOException {
        StatusLine status = response.getStatusLine();
        if (status.getStatusCode() >= 300) {
            throw new NoHttpResponseException(
                    "Did not receive successful HTTP response: status code = " + status.getStatusCode() +
                            ", status message = [" + status.getReasonPhrase() + "]");
        }
    }

    protected boolean isGzipResponse(HttpResponse httpResponse) {
        Header encodingHeader = httpResponse.getFirstHeader(HTTP_HEADER_CONTENT_ENCODING);
        return (encodingHeader != null && encodingHeader.getValue() != null &&
                encodingHeader.getValue().toLowerCase().contains(ENCODING_GZIP));
    }
}
