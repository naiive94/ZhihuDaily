package com.naiive.zhihudaily.common.api;

/**
 * Created by wangzhe on 16/5/22.
 */
public class ZhihuFactory {

    private static ZhihuApi zhihuApi;

    private ZhihuFactory() {
    }

    public static ZhihuApi getApi() {
        if (zhihuApi == null) {
            zhihuApi = new ZhihuRetrofit().getZhihuApi();
        }
        return zhihuApi;
    }


}
