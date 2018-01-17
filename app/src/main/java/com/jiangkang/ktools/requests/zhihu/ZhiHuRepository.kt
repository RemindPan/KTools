package com.jiangkang.ktools.requests.zhihu

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.jiangkang.requests.KRequests
import com.jiangkang.requests.zhihu.ZhihuApi
import com.jiangkang.requests.zhihu.bean.Story
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

/**
 * Created by jiangkang on 2018/1/17.
 * description：
 */

object ZhiHuRepository {

    public fun getZhiHuStoryList(): LiveData<ArrayList<Story>> {
        val data = MutableLiveData<ArrayList<Story>>()

        KRequests.request(ZhihuApi::class.java).latestNews
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    data.value = it.stories
                })

        return data

    }

}