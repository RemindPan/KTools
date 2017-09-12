package com.jiangkang.ktools.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;

import com.jiangkang.ktools.R;
import com.jiangkang.tools.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchViewActivity extends AppCompatActivity {


    @BindView(R.id.search_view_demo)
    SearchView searchViewDemo;

    public static void launch(Context context, Bundle bundle) {
        Intent intent = new Intent(context, SearchViewActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);
        ButterKnife.bind(this);

        initSearchView();
    }

    private void initSearchView() {
        searchViewDemo.setSubmitButtonEnabled(true);
        searchViewDemo.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ToastUtils.showLongToast(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ToastUtils.showShortToast(newText);
                return false;
            }
        });
    }
}
