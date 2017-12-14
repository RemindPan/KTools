package com.jiangkang.ktools;

import android.app.Application;
import android.content.Intent;
import android.os.StrictMode;
import android.support.multidex.MultiDex;
import android.webkit.WebView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.facebook.stetho.Stetho;
import com.jiangkang.tools.King;
import com.jiangkang.tools.service.ScanMusicService;
import com.squareup.leakcanary.LeakCanary;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author jiangkang
 * @date 2017/9/6
 */
public class KApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        MultiDex.install(this);

        enableStrictMode();

        if (BuildConfig.DEBUG) {
            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            Stetho.initializeWithDefaults(KApplication.this);
                        }
                    }
            ).start();

        }

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
        King.init(this);

        initARouter();

    }

    private void initARouter() {
        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this);
    }

    private void enableStrictMode() {
        if (BuildConfig.DEBUG) {
            enableThreadPolicy();
            enableVmPolicy();
        }
    }

    private void enableVmPolicy() {
        StrictMode.setVmPolicy(
                new StrictMode.VmPolicy.Builder()
                        .detectAll()
                        .penaltyLog()
                        .build()
        );
    }

    private void enableThreadPolicy() {
        StrictMode.setThreadPolicy(
                new StrictMode.ThreadPolicy.Builder()
                        .detectAll()
                        .penaltyLog()
                        .build()
        );
    }


}
