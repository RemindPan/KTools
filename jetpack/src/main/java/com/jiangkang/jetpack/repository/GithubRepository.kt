package com.jiangkang.jetpack.repository

import com.jiangkang.jetpack.api.GithubSearchApi
import com.jiangkang.jetpack.data.GithubSearchRepositoryBean
import com.jiangkang.requests.KRequests
import io.reactivex.schedulers.Schedulers

class GithubRepository {

    fun getGithubTrendList(topic: String, onSuccess: (result: GithubSearchRepositoryBean) -> Unit, onError: (t: Throwable) -> Unit) {
        KRequests.request(GithubSearchApi::class.java)
                .getGithubTrendListByStars(topic)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(onSuccess, onError)


    }

}