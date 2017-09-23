package com.jiangkang.tools.widget;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.RemoteViews;
import com.jiangkang.tools.King;

/**
 * Created by jiangkang on 2017/9/23.
 * description：快速创建通知栏
 */

public class KNotification {

  private static NotificationManager sManager;

  static {
    sManager = (NotificationManager) King.getApplicationContext()
        .getSystemService(Context.NOTIFICATION_SERVICE);
  }

  public static void createNotification(Context context,@DrawableRes int smallIconID,  String title, String content, Intent intent){
    PendingIntent pendingIntent = PendingIntent.getActivity(context,1111,intent,PendingIntent.FLAG_UPDATE_CURRENT);

    Notification notification = new Notification.Builder(context)
        .setContentTitle(title)
        .setContentText(content)
        .setSmallIcon(smallIconID)
        .setAutoCancel(true)
        .setContentIntent(pendingIntent)
        .build();

    sManager.notify(0,notification);
  }


  @TargetApi(Build.VERSION_CODES.N)
  public static void createNotification(Context context,@DrawableRes int smallIconID,RemoteViews bigView,Intent intent){
    PendingIntent pendingIntent = PendingIntent.getActivity(context,1111,intent,PendingIntent.FLAG_UPDATE_CURRENT);

    Notification notification = new Notification.Builder(context)
        .setSmallIcon(smallIconID)
        .setAutoCancel(true)
        .setContentIntent(pendingIntent)
        .setCustomBigContentView(bigView)
        .build();

    sManager.notify(0,notification);
  }



}
