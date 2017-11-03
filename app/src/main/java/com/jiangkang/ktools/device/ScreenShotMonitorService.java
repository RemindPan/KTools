package com.jiangkang.ktools.device;

import android.app.Service;
import android.content.Intent;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.provider.MediaStore;

public class ScreenShotMonitorService extends Service {


    private HandlerThread mHandlerThread;

    private Handler mHandler;

    private ContentObserver mInternalObserver;

    private ContentObserver mExternalObserver;


    public ScreenShotMonitorService() {

    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }


    @Override
    public void onCreate() {
        super.onCreate();

        initHandler();

        initObserver();

        addObservers();
    }

    private void addObservers() {
        getContentResolver().registerContentObserver(
                MediaStore.Images.Media.INTERNAL_CONTENT_URI,
                false,
                mInternalObserver);
        getContentResolver().registerContentObserver(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                false,
                mExternalObserver
        );
    }

    private void initHandler() {
        mHandlerThread = new HandlerThread("ScreenShot");
        mHandlerThread.start();
        mHandler = new Handler(mHandlerThread.getLooper());
    }

    private void initObserver() {
        mInternalObserver = new MediaContentObserver(this,mHandler, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        mExternalObserver = new MediaContentObserver(this,mHandler, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        removeObservers();
    }

    private void removeObservers() {
        getContentResolver().unregisterContentObserver(mInternalObserver);
        getContentResolver().unregisterContentObserver(mExternalObserver);
    }

}
