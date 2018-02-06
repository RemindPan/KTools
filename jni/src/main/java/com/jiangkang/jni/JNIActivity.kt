package com.jiangkang.jni

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jiangkang.tools.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_jni.*

class JNIActivity : AppCompatActivity() {

    companion object {
        init {
            System.loadLibrary("hello-lib")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jni)
        handleClick()
    }

    private fun handleClick() {

        btnJniHelloWorld.setOnClickListener {
            ToastUtils.showShortToast(helloWorld()!!)
        }

    }

    private external fun helloWorld(): String

    private external fun sumInt(a: Int, b: Int): Int


}
