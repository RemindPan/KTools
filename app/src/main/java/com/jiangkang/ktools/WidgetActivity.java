package com.jiangkang.ktools;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.jiangkang.ktools.widget.SearchViewActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WidgetActivity extends AppCompatActivity {


    @BindView(R.id.btn_search_view)
    Button btnSearchView;
    @BindView(R.id.btn_tab_layout)
    Button btnTabLayout;

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
        SearchViewActivity.launch(this,null);
    }

    @OnClick(R.id.btn_tab_layout)
    public void onBtnTabLayoutClicked() {
    }
}
