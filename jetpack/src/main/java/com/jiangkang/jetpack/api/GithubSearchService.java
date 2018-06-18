package com.jiangkang.jetpack.api;

import com.jiangkang.jetpack.data.GithubSearchRepositoryBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GithubSearchService {

    @GET("/search/repositories?sort=stars")
    Flowable<GithubSearchRepositoryBean> getSearchTrendListByStars(@Query("q") String topic);

}
