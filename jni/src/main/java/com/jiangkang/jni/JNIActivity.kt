package com.jiangkang.jni

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jiangkang.tools.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_jni.*

class JNIActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jni)
        handleClick()
    }

    private fun handleClick() {

        btnJniHelloWorld.setOnClickListener {
            ToastUtils.showShortToast("not done!")
        }


    }

}
