package com.jiangkang.ktools.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.jiangkang.ktools.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FabActivity extends AppCompatActivity {

    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.iv_fake_fab)
    ImageView ivFakeFab;

    public static void launch(Context context, Bundle bundle) {
        Intent intent = new Intent(context, FabActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.fab)
    public void onFabClicked() {
        Snackbar.make(mFab, "我一出现，FloatingActionButton也要向上移动", Snackbar.LENGTH_SHORT).show();
    }

    @OnClick(R.id.iv_fake_fab)
    public void onFakeFabClicked() {
        Snackbar.make(ivFakeFab,"这是一个假的FloatingActionButton",Snackbar.LENGTH_SHORT).show();
    }
}
