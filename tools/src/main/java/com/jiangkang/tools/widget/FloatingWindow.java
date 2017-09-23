package com.jiangkang.tools.widget;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.jiangkang.tools.R;

/**
 * Created by jiangkang on 2017/9/23.
 */

public class FloatingWindow {

    private static WindowManager sWindowManager;

    private static WindowManager.LayoutParams sWindowLayoutParams;

    private static View sView;

    public static void init(final Context context){
        sWindowManager = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        sWindowLayoutParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                Build.VERSION.SDK_INT > Build.VERSION_CODES.N?
                        WindowManager.LayoutParams.TYPE_PHONE: WindowManager.LayoutParams.TYPE_TOAST,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                PixelFormat.TRANSLUCENT
        );
        sWindowLayoutParams.gravity = Gravity.TOP + Gravity.RIGHT;
        sView = LayoutInflater.from(context).inflate(R.layout.layout_floating_window,null);
    }

    public static void show(Context context, final String content){
        if (sWindowManager == null){
            init(context);
        }
        TextView tvContent = (TextView) sView.findViewById(R.id.tv_window_content);
        tvContent.setText(content);
        if (!sView.isAttachedToWindow()){
            sWindowManager.addView(sView,sWindowLayoutParams);
        }
    }


    public static void dismiss(){
        if (sWindowManager != null && sView != null && sView.isAttachedToWindow()){
            sWindowManager.removeView(sView);
        }
    }


}
