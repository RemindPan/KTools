package com.jiangkang.ktools;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jiangkang.ktools.widget.SearchViewActivity;
import com.jiangkang.tools.utils.ToastUtils;
import com.jiangkang.tools.widget.FloatingWindow;

import java.util.Timer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WidgetActivity extends AppCompatActivity {


    @BindView(R.id.btn_search_view)
    Button btnSearchView;

    @BindView(R.id.btn_tab_layout)
    Button btnTabLayout;
    @BindView(R.id.btn_show_floating_window)
    Button btnShowFloatingWindow;
    @BindView(R.id.btn_dismiss_floating_window)
    Button btnDismissFloatingWindow;


    public static void launch(Context context, Bundle bundle) {
        Intent intent = new Intent(context, WidgetActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.btn_search_view)
    public void onBtnSearchViewClicked() {
        SearchViewActivity.launch(this, null);
    }

    @OnClick(R.id.btn_tab_layout)
    public void onBtnTabLayoutClicked() {
        ToastUtils.showToast("测试自定义显示时间Toast",9000);
    }


    @OnClick(R.id.btn_show_floating_window)
    public void onBtnShowFloatingWindowClicked() {
        FloatingWindow.show(this,"来来来，看这里\n这是一个悬浮框");
    }

    @OnClick(R.id.btn_dismiss_floating_window)
    public void onBtnDismissFloatingWindowClicked() {
        FloatingWindow.dismiss();
    }



}
