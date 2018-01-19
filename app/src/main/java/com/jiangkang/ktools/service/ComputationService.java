package com.jiangkang.ktools.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.jiangkang.ktools.IComputation;

/**
 * Created by jiangkang on 2018/1/18.
 * description：Android 服务端 IComputation 的Service
 */

public class ComputationService extends Service{

    private Binder mBinder = new IComputation.Stub() {
        @Override
        public float add(float a, float b) throws RemoteException {
            return a + b;
        }

        @Override
        public float sub(float a, float b) throws RemoteException {
            return a - b;
        }

        @Override
        public float mul(float a, float b) throws RemoteException {
            return a * b;
        }

        @Override
        public float del(float a, float b) throws RemoteException {
            if (b == 0) throw new RemoteException("不能除以0");
            return a / b;
        }
    };


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }


    @Override
    public void onCreate() {
        super.onCreate();
    }


}
