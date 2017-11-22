package com.jiangkang.ktools.data.db;

import com.jiangkang.ktools.data.Repository;
import com.jiangkang.requests.zhihu.bean.LatestNews;

import io.reactivex.Flowable;

/**
 * Created by jiangkang on 2017/11/8.
 * description：
 */

public interface DbRepository extends Repository {

    Flowable<LatestNews> getLatestNews();

}
