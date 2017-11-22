package com.jiangkang.ktools;

import android.app.Application;
import android.content.Intent;
import android.os.StrictMode;

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
//                .detectActivityLeaks()
//                .detectLeakedSqlLiteObjects()
//                .detectContentUriWithoutPermission()
//                .detectCleartextNetwork()
//                .detectFileUriExposure()
//                .detectLeakedClosableObjects()
//                .detectLeakedRegistrationObjects()
//                .detectUntaggedSockets()
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
