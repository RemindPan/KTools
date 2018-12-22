package com.jiangkang.container

import android.R
import android.support.v4.app.FragmentActivity
import com.jiangkang.container.fragment.ContainerFragment
import com.jiangkang.container.fragment.ViewDataBinder

fun FragmentActivity.loadFragment(layoutId: Int, title: String? = null, viewBinder: ViewDataBinder? = null) {
    val fragment = ContainerFragment.newInstance(layoutId, title).apply {
        viewDataBinder = viewBinder
    }
    supportFragmentManager
            .beginTransaction()
            .replace(R.id.content, fragment)
            .commitAllowingStateLoss()
}
