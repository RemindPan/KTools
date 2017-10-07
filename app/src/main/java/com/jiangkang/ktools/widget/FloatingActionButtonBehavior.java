package com.jiangkang.ktools.widget;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by jiangkang on 2017/10/7.
 */

public class FloatingActionButtonBehavior extends CoordinatorLayout.Behavior<ImageView> {


    public FloatingActionButtonBehavior() {
    }

    public FloatingActionButtonBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, ImageView child, View dependency) {
        return dependency instanceof Snackbar.SnackbarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, ImageView child, View dependency) {
        float translationY = dependency.getTranslationY() - dependency.getHeight();
        child.setTranslationY(translationY);
        return true;
    }



}
