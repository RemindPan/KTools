package com.jiangkang.requests.github

import com.jiangkang.requests.BaseApi

class GithubApi : BaseApi<GithubService>() {

    val BASE_URL = "https://api.github.com/"

    override fun getBaseUrl(): String = BASE_URL


}