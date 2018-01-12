package com.jiangkang.widget.view;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by jiangkang on 2017/12/27.
 * description：自定义View - 太极图
 */

public class TaiChiView extends View {

    private Paint mPaintWhite;

    private Paint mPaintBlack;

    //旋转角度
    private int mDegrees;

    public TaiChiView(Context context) {
        super(context);
        init();
    }

    public TaiChiView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TaiChiView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TaiChiView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        mPaintBlack = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintWhite = new Paint(Paint.ANTI_ALIAS_FLAG);

        mPaintWhite.setColor(Color.WHITE);
        mPaintBlack.setColor(Color.BLACK);

        //默认背景颜色
        setBackgroundColor(Color.GRAY);
    }


    @Override
    protected void onDraw(Canvas canvas) {

        int width = getWidth();
        int height = getHeight();

        //平移画布到View的中间
        canvas.translate(width / 2, height / 2);

        //底色
        canvas.drawColor(Color.GRAY);

        //旋转，这里mDegrees与动画相关联
        canvas.rotate(mDegrees,0,0);

        //两个半圆
        int radius = Math.min(width, height) / 2 - 40;
        RectF rectF = new RectF(-radius, -radius, radius, radius);
        canvas.drawArc(rectF, 90, 180, true, mPaintBlack);
        canvas.drawArc(rectF, -90, 180, true, mPaintWhite);

        //两个小圆
        int smallRadius = radius / 2;
        canvas.drawCircle(0, -smallRadius, smallRadius, mPaintBlack);
        canvas.drawCircle(0, smallRadius, smallRadius, mPaintWhite);

        //两个小点
        int dotRadius = smallRadius / 4;
        canvas.drawCircle(0, -smallRadius, dotRadius, mPaintWhite);
        canvas.drawCircle(0, smallRadius, dotRadius, mPaintBlack);

    }

    /*
    * ，添加动画，让太极图动起来
    * */
    public void startRotate() {
        ValueAnimator animator = ValueAnimator.ofInt(0,360);
        animator.setDuration(2000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mDegrees = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.start();
    }


}
