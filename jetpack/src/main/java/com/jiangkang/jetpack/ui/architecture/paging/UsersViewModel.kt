package com.jiangkang.jetpack.ui.architecture.paging

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.jiangkang.requests.KRequests
import com.jiangkang.requests.github.GithubApi
import com.jiangkang.requests.github.GithubService
import com.jiangkang.requests.github.entity.User
import io.reactivex.disposables.CompositeDisposable

class UsersViewModel : ViewModel() {

    var userList: LiveData<PagedList<User>>

    private val compositeDisposable = CompositeDisposable()

    private val pageSize = 15

    private val sourceFactory: UsersDataSourceFactory

    init {
        sourceFactory = UsersDataSourceFactory(compositeDisposable, GithubService.getService())
        val config = PagedList.Config.Builder()
                .setPageSize(pageSize)
                .setInitialLoadSizeHint(pageSize * 2)
                .setEnablePlaceholders(false)
                .build()
        userList = LivePagedListBuilder<Long, User>(sourceFactory, config).build()

    }

    fun retry() {
        sourceFactory.usersDataSourceLiveData.value!!.retry()
    }

    fun refresh() {
        sourceFactory.usersDataSourceLiveData.value!!.invalidate()
    }

    fun getNetworkState(): LiveData<NetworkState> = Transformations.switchMap<UserDataSource, NetworkState>(
            sourceFactory.usersDataSourceLiveData, { it.networkState })

    fun getRefreshState(): LiveData<NetworkState> = Transformations.switchMap<UserDataSource, NetworkState>(
            sourceFactory.usersDataSourceLiveData, { it.initialLoad })

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}