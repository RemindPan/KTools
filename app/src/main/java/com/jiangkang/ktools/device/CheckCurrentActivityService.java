package com.jiangkang.ktools.device;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

import com.jiangkang.ktools.R;
import com.jiangkang.tools.device.DeviceUtils;
import com.jiangkang.tools.utils.AppUtils;

public class CheckCurrentActivityService extends Service {


    NotificationManager manager;


    public CheckCurrentActivityService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        registerReceiver(new ActivityChangeReceiver(),new IntentFilter("com.jiangkang.ktools.ActivityChange"));
    }








}
