package com.jiangkang.requests.zhihu;

import com.jiangkang.requests.BaseApi;
import com.jiangkang.requests.zhihu.bean.LatestNews;
import com.jiangkang.requests.zhihu.bean.StartPageInfo;

import io.reactivex.Flowable;

/**
 * Created by jiangkang on 2017/10/21.
 */

public class ZhihuApi extends BaseApi<ZhihuService>{

    private static final String BASE_URL = "http://news-at.zhihu.com/api/4/";

    @Override
    protected String getBaseUrl() {
        return BASE_URL;
    }


    public Flowable<LatestNews> getLatestNews(){
        return mService.getLatestDailyNews();
    }



}
