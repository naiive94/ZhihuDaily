package com.naiive.zhihudaily.common.api;

/**
 * Created by wangzhe on 16/5/22.
 */
public class ZhihuFactory {

    private static ZhihuApi zhihuApi;
    private static CommonApi commonApi;

    private ZhihuFactory() {
    }

    public static ZhihuApi getApi() {
        if (zhihuApi == null) {
            zhihuApi = new ZhihuRetrofit().getZhihuApi();
        }
        return zhihuApi;
    }

    public static CommonApi getCommonApi(String fullUrl){
        if (commonApi == null){
            commonApi = new ZhihuRetrofit().getCommonApi(fullUrl);
        }
        return commonApi;
    }


}
