package com.jiangkang.ktools.device;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.jiangkang.ktools.R;
import com.jiangkang.tools.utils.AppUtils;
import com.jiangkang.tools.widget.FloatingWindow;

public class ActivityChangeReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
       if ("com.jiangkang.ktools.ActivityChange".equals(intent.getAction())){
           createNotification(context, AppUtils.getCurrentActivity());
//           FloatingWindow.dismiss();
//           FloatingWindow.show(context,AppUtils.getCurrentActivity());
       }
    }


    private void createNotification(Context context, String info){
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        final Notification notification = new Notification.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("当前Activity")
                .setContentText(info)
                .build();
        manager.notify(0,notification);
    }




}
