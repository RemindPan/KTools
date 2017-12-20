package com.jiangkang.ktools.rxjava

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jiangkang.ktools.DownloadActivity
import com.jiangkang.ktools.rxjava.fragments.RxJavaFragment


class RxJavaActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fragmentManager
                .beginTransaction()
                .replace(android.R.id.content, RxJavaFragment(), this.toString())
                .commit()
    }


}

