package com.jiangkang.ktools.widget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.widget.ImageView;

import com.jiangkang.ktools.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CoordinatorActivity extends AppCompatActivity {

    @BindView(R.id.app_bar_image)
    ImageView mAppBarImage;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;
    @BindView(R.id.app_bar_layout)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.scroll_view)
    NestedScrollView mScrollView;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.web_view)
    WebView mWebView;

    public static void launch(Context context, Bundle bundle) {
        Intent intent = new Intent(context, CoordinatorActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator);
        ButterKnife.bind(this);

        initViews();

    }

    private void initViews() {

        mToolbar.setTitle("Demo");
        mToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("http://www.jianshu.com/u/2c22c64b9aff");
    }

    @OnClick(R.id.fab)
    public void onFabClicked() {
        mWebView.reload();
    }
}
