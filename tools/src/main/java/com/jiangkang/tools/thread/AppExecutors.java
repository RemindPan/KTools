package com.jiangkang.tools.thread;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecutors {

    private final Executor mDiskIO;

    private final Executor mNetwork;

    private final Executor mMainThread;

    private AppExecutors(Executor mDiskIO, Executor mNetwork, Executor mMainThread) {
        this.mDiskIO = mDiskIO;
        this.mNetwork = mNetwork;
        this.mMainThread = mMainThread;
    }

    public AppExecutors() {
        this(
                Executors.newSingleThreadExecutor(),
                Executors.newFixedThreadPool(3),
                new MainThreadExecutor()
        );
    }

    public Executor diskIO() {
        return mDiskIO;
    }

    public Executor network() {
        return mNetwork;
    }


    public Executor mainThread() {
        return mMainThread;
    }


    private static class MainThreadExecutor implements Executor {

        private final Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }
}
