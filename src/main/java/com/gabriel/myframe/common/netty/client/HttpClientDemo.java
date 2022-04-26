package com.gabriel.myframe.common.netty.client;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;


public class HttpClientDemo {
    public static void main(String[] args) {
        String url = "http://localhost:8080/hello";
        try {
            String responseBody = getMethodWithHttpClient(url);
            System.out.println("response: " + responseBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getMethodWithHttpClient(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
        } finally {
            if (response != null) {
                response.close();
            }
            httpClient.close();
        }
    }
}
