package com.jiangkang.jetpack.worker

import android.util.Log
import androidx.work.Worker
import com.jiangkang.tools.utils.LogUtils
import com.jiangkang.tools.utils.ToastUtils

class CompressWorker : Worker() {

    val tag = "CompressWorker"

    override fun doWork(): WorkerResult {

        Log.d(tag,"doing the worker!!!")

        ToastUtils.showShortToast("doing the worker")

        return WorkerResult.RETRY

    }


}