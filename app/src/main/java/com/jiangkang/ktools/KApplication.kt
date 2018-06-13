package com.jiangkang.ktools

import android.app.Application
import android.content.Context
import android.os.Debug
import android.os.StrictMode
import android.support.multidex.MultiDex
import com.facebook.drawee.backends.pipeline.Fresco
import com.github.anrwatchdog.ANRWatchDog
import com.jiangkang.tools.King
import com.squareup.leakcanary.LeakCanary

/**
 * @author jiangkang
 * @date 2017/9/6
 */
open class KApplication : Application() {


    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)

        // TODO: 2018/1/30 测试框架与MultiDex不兼容，待处理
        MultiDex.install(this)

//        try {
//            HookUtils.attachBaseContext()
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }

    }

    override fun onCreate() {
        Debug.startMethodTracing()
        super.onCreate()

        enableStrictMode()

        initLeakCanary()

        King.init(this)

        initANRWatchDog()

//        initTool()

        Debug.stopMethodTracing()


        Fresco.initialize(this)

    }

//    private fun initTool() {
//        if (BuildConfig.DEBUG) {
//            AndroidDevMetrics.initWith(this.applicationContext)
//        }
//    }

    private fun initANRWatchDog() {
        ANRWatchDog().start()
    }

    private fun initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
    }

//    private fun initWeex() {
//
//        val config = InitConfig.Builder()
//                .setImgAdapter(ImageAdapter())
//                .build()
//
//        WXSDKEngine.initialize(this, config)
//
//    }

//    private fun initARouter() {
//        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
//            ARouter.openLog()     // 打印日志
//            ARouter.openDebug()   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
//        }
//        ARouter.init(this)
//    }

    private fun enableStrictMode() {
        if (BuildConfig.DEBUG) {
            enableThreadPolicy()
            enableVmPolicy()
        }
    }

    private fun enableVmPolicy() {
        StrictMode.setVmPolicy(
                StrictMode.VmPolicy.Builder()
                        .detectAll()
                        .penaltyLog()
                        .build()
        )
    }

    private fun enableThreadPolicy() {
        StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder()
                        .detectAll()
                        .penaltyLog()
                        .build()
        )
    }


}
