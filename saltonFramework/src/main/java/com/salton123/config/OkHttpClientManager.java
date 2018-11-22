package com.salton123.config;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class OkHttpClientManager {
    private static final String TAG = OkHttpClientManager.class.getName();
    protected OkHttpClient okHttpClient;
    private static boolean isShowReqLog = false;

    public static void setDebug(boolean isDebug) {
        isShowReqLog = isDebug;
    }

    private OkHttpClientManager() {
        if (okHttpClient == null) {
            OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient().newBuilder();
            if (isShowReqLog) {
                okHttpClientBuilder.addInterceptor(new RequestInterceptor());
            }
            okHttpClientBuilder.connectTimeout(10, TimeUnit.SECONDS);
            okHttpClientBuilder.readTimeout(15, TimeUnit.SECONDS);
            okHttpClient = okHttpClientBuilder.build();
        }
    }

    public static OkHttpClientManager getInstance() {
        return HelpHolder.INSTANCE;
    }

    private static class HelpHolder {
        private static final OkHttpClientManager INSTANCE = new OkHttpClientManager();
    }
}
