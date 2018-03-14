package com.jiangkang.ktools

import android.app.Application
import android.content.Context
import android.os.StrictMode
import android.support.multidex.MultiDex

import com.alibaba.android.arouter.launcher.ARouter
import com.facebook.stetho.Stetho
import com.jiangkang.hack.HookUtils
import com.jiangkang.tools.King
import com.jiangkang.weex.ImageAdapter
import com.taobao.weex.InitConfig
import com.taobao.weex.WXSDKEngine

import java.util.concurrent.Executors

/**
 * @author jiangkang
 * @date 2017/9/6
 */
class KApplication : Application() {


    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)

        // TODO: 2018/1/30 测试框架与MultiDex不兼容，待处理
        MultiDex.install(this)

        try {
            HookUtils.attachBaseContext()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun onCreate() {
        super.onCreate()

        enableStrictMode()

        initLeakCanary()

        King.init(this)

        Executors.newCachedThreadPool().execute {
            if (BuildConfig.DEBUG) {
                Stetho.initializeWithDefaults(this@KApplication)
            }
            initARouter()
        }


        initWeex()

    }

    private fun initLeakCanary() {
        //        if (LeakCanary.isInAnalyzerProcess(this)) {
        //            return;
        //        }
        //        LeakCanary.install(this);
    }

    private fun initWeex() {

        val config = InitConfig.Builder()
                .setImgAdapter(ImageAdapter())
                .build()

        WXSDKEngine.initialize(this, config)

    }

    private fun initARouter() {
        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog()     // 打印日志
            ARouter.openDebug()   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this)
    }

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
