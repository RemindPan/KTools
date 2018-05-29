package com.jiangkang.jetpack.ui.architecture.paging

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.ItemKeyedDataSource
import com.jiangkang.requests.github.GithubService
import com.jiangkang.requests.github.entity.User
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.internal.disposables.ArrayCompositeDisposable
import io.reactivex.schedulers.Schedulers

class UserDataSource(
        private val githubService: GithubService,
        private val compositeDisposable: CompositeDisposable
) : ItemKeyedDataSource<Long, User>() {

    val networkState = MutableLiveData<NetworkState>()

    val initialLoad = MutableLiveData<NetworkState>()

    /**
     * Keep Completable reference for the retry event
     */
    private var retryCompletable: Completable? = null

    fun retry() {
        if (retryCompletable != null) {
            compositeDisposable.add(retryCompletable!!
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ }, { }))
        }
    }

    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<User>) {
        // update network states.
        // we also provide an initial load state to the listeners so that the UI can know when the
        // very first list is loaded.
        networkState.postValue(NetworkState.LOADING)
        initialLoad.postValue(NetworkState.LOADING)

        //get the initial users from the api
        compositeDisposable.add(githubService.getUsers(1, params.requestedLoadSize).subscribe({ users ->
            // clear retry since last request succeeded
            setRetry(null)
            networkState.postValue(NetworkState.LOADED)
            initialLoad.postValue(NetworkState.LOADED)
            callback.onResult(users)
        }, { throwable ->
            // keep a Completable for future retry
            setRetry(Action { loadInitial(params, callback) })
            val error = NetworkState.error(throwable.message)
            // publish the error
            networkState.postValue(error)
            initialLoad.postValue(error)
        }))
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<User>) {
        // set network value to loading.
        networkState.postValue(NetworkState.LOADING)

        //get the users from the api after id
        compositeDisposable.add(githubService.getUsers(params.key, params.requestedLoadSize).subscribe({ users ->
            // clear retry since last request succeeded
            setRetry(null)
            networkState.postValue(NetworkState.LOADED)
            callback.onResult(users)
        }, { throwable ->
            // keep a Completable for future retry
            setRetry(Action { loadAfter(params, callback) })
            // publish the error
            networkState.postValue(NetworkState.error(throwable.message))
        }))
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<User>) {
        // ignored, since we only ever append to our initial load
    }

    override fun getKey(item: User): Long {
        return item.id
    }

    private fun setRetry(action: Action?) {
        if (action == null) {
            this.retryCompletable = null
        } else {
            this.retryCompletable = Completable.fromAction(action)
        }
    }

}