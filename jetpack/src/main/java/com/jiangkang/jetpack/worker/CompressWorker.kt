package com.jiangkang.jetpack.worker

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import androidx.work.Worker
import com.jiangkang.tools.utils.LogUtils
import com.jiangkang.tools.utils.ToastUtils

class CompressWorker : Worker() {

    val tag = "CompressWorker"

    override fun doWork(): WorkerResult {

        Log.d(tag,"doing the worker!!!")

        ToastUtils.showShortToast("doing the worker")

        val vibrator = applicationContext.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(500,VibrationEffect.DEFAULT_AMPLITUDE))
        }else{
            vibrator.vibrate(500)
        }


        return WorkerResult.SUCCESS

    }


}