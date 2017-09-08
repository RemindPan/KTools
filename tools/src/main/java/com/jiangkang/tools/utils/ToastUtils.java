package com.jiangkang.tools.utils;

import android.widget.Toast;

import com.jiangkang.tools.King;

/**
 * Created by jiangkang on 2017/9/8.
 */

public class ToastUtils {

    public static void showShortToast(String msg){
        Toast.makeText(King.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public static void showLongToast(String msg){
        Toast.makeText(King.getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }


}
