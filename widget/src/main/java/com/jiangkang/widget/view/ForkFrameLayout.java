package com.jiangkang.widget.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;

/**
 * Created by jiangkang on 2017/12/27.
 * description：
 */

public class ForkFrameLayout extends ViewGroup {


    public ForkFrameLayout(Context context) {
        super(context);
    }

    public ForkFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ForkFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ForkFrameLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int count = getChildCount();

        int maxWidth;
        int maxHeight;
        int childState;

        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                measureChildWithMargins(child,widthMeasureSpec,0,heightMeasureSpec,0);
                LayoutParams lp = child.getLayoutParams();
            }

        }



    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }


}
