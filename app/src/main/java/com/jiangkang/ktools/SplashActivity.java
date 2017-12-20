package com.jiangkang.ktools;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.viewpager_splash)
    ViewPager mViewpagerSplash;

    private View item0, item1, item2;

    private ArrayList<View> views;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        initViewPager();

        setupTimer();

    }

    private void setupTimer() {
        final CountDownTimer timer = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                MainActivity.launch(SplashActivity.this);
                SplashActivity.this.finish();
            }
        };
        timer.start();
    }

    private void initViewPager() {
        LayoutInflater inflater = LayoutInflater.from(this);

        item0 = inflater.inflate(R.layout.item_splash_image, null);
        item1 = inflater.inflate(R.layout.item_splash_image, null);
        item2 = inflater.inflate(R.layout.item_splash_image, null);

        views = new ArrayList<>();

        views.add(item0);
        views.add(item1);
        views.add(item2);

        PagerAdapter adapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return views.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }


            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(views.get(position));
                return views.get(position);
            }


            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(views.get(position));
            }
        };

        mViewpagerSplash.setAdapter(adapter);

    }


}
