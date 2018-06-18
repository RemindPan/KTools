package com.jiangkang.jetpack.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.jiangkang.jetpack.repository.GithubRepository

class GithubTrendViewModelFactory(private val repository: GithubRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GithubTrendViewModel(repository) as T
    }
}