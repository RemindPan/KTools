package com.jiangkang.requests.zhihu;

import com.jiangkang.requests.zhihu.bean.LatestNews;
import com.jiangkang.requests.zhihu.bean.StartPageInfo;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by jiangkang on 2017/10/21.
 */

public interface ZhihuService {

    /*
    * 获取最新日报
    * */
    @GET("news/latest")
    Flowable<LatestNews> getLatestDailyNews();



}
