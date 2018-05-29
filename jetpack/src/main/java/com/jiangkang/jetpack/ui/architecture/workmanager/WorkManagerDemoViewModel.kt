package com.jiangkang.jetpack.ui.architecture.workmanager

import android.arch.lifecycle.ViewModel
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.jiangkang.jetpack.worker.CompressWorker

open class WorkManagerDemoViewModel : ViewModel() {

    open fun downloadWhenWifi() {

        val request = OneTimeWorkRequest.Builder(CompressWorker::class.java)
                .build()

        WorkManager.getInstance().enqueue(request)

        WorkManager.getInstance().getStatusById(request.id)


    }

}
