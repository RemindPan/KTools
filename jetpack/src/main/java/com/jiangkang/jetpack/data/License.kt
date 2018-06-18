package com.jiangkang.jetpack.data

import java.io.Serializable

data class License(
        val key: String,
        val name: String,
        val spdx_id: String,
        val url: String,
        val node_id: String
): Serializable