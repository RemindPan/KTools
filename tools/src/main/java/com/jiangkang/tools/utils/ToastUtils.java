package com.jiangkang.tools.utils;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.jiangkang.tools.King;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by jiangkang on 2017/9/8.
 */

public class ToastUtils {


    public static void showShortToast(final String msg) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(King.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void showLongToast(final String msg) {
        new Handler(Looper.getMainLooper())
                .post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(King.getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                    }
                });

    }


    public static void showToast(String msg, int duration) {
        final Timer timer = new Timer();
        final Toast toast = Toast.makeText(King.getApplicationContext(), msg, Toast.LENGTH_LONG);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                toast.show();
            }
        }, 0, 3500);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                toast.cancel();
                timer.cancel();
            }
        }, duration);

    }

}
