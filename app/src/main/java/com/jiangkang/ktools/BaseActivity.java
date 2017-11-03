package com.jiangkang.ktools;

import android.database.ContentObserver;
import android.os.Handler;
import android.os.HandlerThread;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jiangkang.ktools.device.MediaContentObserver;

public class BaseActivity extends AppCompatActivity {

    private HandlerThread mHandlerThread;

    private Handler handler;

    private ContentObserver mInternalObserver;

    private ContentObserver mExternalObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        handler = new Handler(mHandlerThread.getLooper());
    }

    private void initObserver() {
        mInternalObserver = new MediaContentObserver(this,handler, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        mExternalObserver = new MediaContentObserver(this,handler, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeObservers();
    }


    private void removeObservers() {
        getContentResolver().unregisterContentObserver(mInternalObserver);
        getContentResolver().unregisterContentObserver(mExternalObserver);
    }
}
