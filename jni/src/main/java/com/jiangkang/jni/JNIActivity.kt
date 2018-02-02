package com.jiangkang.jni

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class JNIActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jni)
    }

    external fun helloWorld():String


}
