package com.jiangkang.hack;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.jiangkang.tools.utils.ToastUtils;

/**
 * Created by jiangkang on 2017/11/28.
 * description：
 */

public class HookOnClickListener implements View.OnClickListener{

    private static final String TAG = "hook";

    private View.OnClickListener originListener;

    private Context context;

    public HookOnClickListener(View.OnClickListener originListener,Context context) {
        this.originListener = originListener;
        this.context = context;
    }

    @Override
    public void onClick(View v) {

        //点击之前
        Log.d(TAG, "onClick: before");
        ToastUtils.showShortToast(context,"点击按钮之前");

        if (originListener != null){
            originListener.onClick(v);
        }


        //点击之后
        Log.d(TAG, "onClick: after");
        ToastUtils.showShortToast(context,"点击按钮之后");

    }
}
