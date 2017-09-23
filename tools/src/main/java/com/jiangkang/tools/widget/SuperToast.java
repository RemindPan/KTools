package com.jiangkang.tools.widget;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by jiangkang on 2017/9/23.
 */

public class SuperToast {

    public Toast mToast;

    private boolean isShowing = false;

    private Object mTN;

    private Method show;

    private Method hide;

    private Context mContext;

    private int mDuration;


    private Handler mHandler = new Handler();


    public SuperToast(Context context) {
        this.mContext = context;
        if (mToast == null){
            mToast = new Toast(context);
        }
    }


    public void show(){
        if (isShowing) return;

        initTN();

        try {
            show.invoke(mTN,null);
            isShowing = true;
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hide();
                }
            },mDuration);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    public void hide(){
        if (!isShowing) return;
        try {
            hide.invoke(mTN,null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        isShowing = false;
    }


    public SuperToast setDuration(int mDuration) {
        this.mDuration = mDuration;
        return this;
    }

    private void initTN(){
        try {
            Field tnField = mToast.getClass().getField("mTN");
            tnField.setAccessible(true);
            mTN = tnField.get(mToast);
            show = mTN.getClass().getMethod("show");
            hide = mTN.getClass().getMethod("hide");

            Field tnNextViewField = mTN.getClass().getField("mNextView");
            tnNextViewField.setAccessible(true);
            tnNextViewField.set(mTN,mToast.getView());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }


}
