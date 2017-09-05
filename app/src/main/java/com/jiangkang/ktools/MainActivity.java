package com.jiangkang.ktools;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

  private FunctionAdapter mAdapter;

  @BindView(R.id.rc_function_list) RecyclerView mRcFunctionList;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    initViews();

  }

  private void initViews() {

    mRcFunctionList.setLayoutManager(new GridLayoutManager(this,4));

    mRcFunctionList.setAdapter(new FunctionAdapter(this));

  }
}
