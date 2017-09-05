package com.jiangkang.ktools;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jiangkang on 2017/9/5.
 * description：
 */

public class SystemActivity extends AppCompatActivity {

  @BindView(R.id.btn_open_contacts) Button mBtnOpenContacts;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_system);
    ButterKnife.bind(this);
  }

  @OnClick(R.id.btn_open_contacts) public void onOpenContactsClicked() {
    Intent intent = new Intent();
    intent.setAction(Intent.ACTION_CALL_BUTTON);
    startActivity(intent);
  }
}
