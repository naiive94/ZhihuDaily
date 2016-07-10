package com.naiive.zhihudaily.utils;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wangzhe on 16/7/9.
 */
public class OkHttpUtil {

    public final OkHttpClient okHttpClient = new OkHttpClient();

    private OkHttpUtil() {
    }

    public static OkHttpUtil getInstance() {
        return OkHttpUtilHolder.INSTANCE;
    }

    private static class OkHttpUtilHolder {
        private static final OkHttpUtil INSTANCE = new OkHttpUtil();
    }

    public String get(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Response response = okHttpClient.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

}
