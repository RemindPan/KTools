package com.jiangkang.tools.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by jiangkang on 2017/9/14.
 */

public class PermissionUtils{

    private static final int REQUEST_CODE = 1111;


    private Context context;

    private PermissionCallback callback;

    private PermissionUtils(Context context) {
        this.context = context;
    }

    public static PermissionUtils getInstance(Context context){
        return new PermissionUtils(context);
    }


    public void requestPermission(@NonNull String permission){
        if (ContextCompat.checkSelfPermission(context,permission) == PackageManager.PERMISSION_GRANTED){
            if (callback != null){
                callback.success();
            }
        }else {
            ActivityCompat.requestPermissions((Activity) context,new String[]{permission},REQUEST_CODE);
        }
    }


    public PermissionUtils setCallback(PermissionCallback callback) {
        this.callback = callback;
        return this;
    }

    public interface PermissionCallback{

        int REQUEST_CODE = 1234;

        void success();
        void fail();
    }


}
