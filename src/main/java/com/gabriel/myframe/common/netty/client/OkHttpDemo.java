package com.gabriel.myframe.common.netty.client;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class OkHttpDemo {
    private static final OkHttpClient httpClient = new OkHttpClient().newBuilder().readTimeout(6, TimeUnit.SECONDS).build();

    public static void main(String[] args) {
        String url = "http://localhost:8080/hello";
        System.out.println(execGetReq(url, new HashMap<>(5)));
    }

    public static String execGetReq(String url, Map<String, String> headers) {
        Request.Builder requestBuilder = new Request.Builder().url(url);
        if (headers != null && headers.size() > 0) {
            headers.entrySet().stream().forEach(header ->
                    requestBuilder.header(header.getKey(), header.getValue()));
        }
        Request request = requestBuilder.url(url).build();
        try {
            Response response = httpClient.newCall(request).execute();
            return response.body() != null ? response.body().string() : "null";
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
