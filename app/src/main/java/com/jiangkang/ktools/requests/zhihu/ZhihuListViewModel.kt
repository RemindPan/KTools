package com.jiangkang.ktools.requests.zhihu

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.jiangkang.requests.zhihu.bean.Story

/**
 * Created by jiangkang on 2018/1/17.
 * description：
 */

class ZhihuListViewModel : AndroidViewModel {

    var storyListObservable: LiveData<ArrayList<Story>> = ZhiHuRepository.getZhiHuStoryList()


    constructor(application: Application) : super(application) {

    }


}