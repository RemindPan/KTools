package com.jiangkang.ktools;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.jiangkang.ktools.web.WebActivity;
import com.jiangkang.tools.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.rc_function_list) RecyclerView mRcFunctionList;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    initViews();
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    menu.add(0, 0, 0, "关于");
    menu.add(0, 1, 1, "源代码");
    menu.add(0, 2, 2, "文章解析");
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case 0:
        AboutActivity.launch(this, null);
        break;
      case 1:
        openBrowser("https://github.com/jiangkang/KTools");
        break;
      case 2:
        openBrowser("http://www.jianshu.com/u/2c22c64b9aff");
        break;
      default:
        break;
    }
    return super.onOptionsItemSelected(item);
  }

  private void openBrowser(String url) {
    Bundle bundle = new Bundle();
    bundle.putString("launchUrl", url);
    WebActivity.launch(this, bundle);
  }

  private void initViews() {

    mRcFunctionList.setLayoutManager(new GridLayoutManager(this, 4));
    mRcFunctionList.setAdapter(new FunctionAdapter(this));

  }
}
