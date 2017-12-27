package com.jiangkang.widget.view;

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

/**
 * Created by jiangkang on 2017/12/27.
 * description：自定义View - 太极图
 */

public class TaiChiView extends View {

    private Paint mPaintWhite;

    private Paint mPaintBlack;

    //旋转角度
    private float mDegrees;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mDegrees += 6;
            setDegrees(mDegrees);
            sendEmptyMessageDelayed(0, 60);
        }
    };

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

        canvas.translate(width / 2, height / 2);

        canvas.drawColor(Color.GRAY);

        canvas.rotate(mDegrees);

        int radius = Math.min(width, height) / 2 - 40;
        RectF rectF = new RectF(-radius, -radius, radius, radius);
        canvas.drawArc(rectF, 90, 180, true, mPaintBlack);
        canvas.drawArc(rectF, -90, 180, true, mPaintWhite);

        //小圆
        int smallRadius = radius / 2;
        canvas.drawCircle(0, -smallRadius, smallRadius, mPaintBlack);
        canvas.drawCircle(0, smallRadius, smallRadius, mPaintWhite);

        int dotRadius = smallRadius / 4;
        canvas.drawCircle(0, -smallRadius, dotRadius, mPaintWhite);
        canvas.drawCircle(0, smallRadius, dotRadius, mPaintBlack);



    }


    public void setDegrees(float mDegrees) {
        this.mDegrees = mDegrees;
        this.invalidate();
    }


    public void startRotate() {
        mHandler.sendEmptyMessageDelayed(0, 20);
    }


}
