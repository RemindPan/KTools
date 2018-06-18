package com.jiangkang.jetpack.api;

import com.jiangkang.jetpack.data.GithubSearchRepositoryBean;
import com.jiangkang.requests.BaseApi;

import io.reactivex.Flowable;

public class GithubSearchApi extends BaseApi<GithubSearchService> {

    @Override
    protected String getBaseUrl() {
        return "https://api.github.com";
    }


    public Flowable<GithubSearchRepositoryBean> getGithubTrendListByStars(String topic){
        return mService.getSearchTrendListByStars(topic);
    }


}
