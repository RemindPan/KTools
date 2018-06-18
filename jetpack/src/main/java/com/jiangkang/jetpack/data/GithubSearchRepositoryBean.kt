package com.jiangkang.jetpack.data

import java.io.Serializable

data class GithubSearchRepositoryBean(
        val total_count: Int,
        val incomplete_results: Boolean,
        val items: List<Item>
): Serializable