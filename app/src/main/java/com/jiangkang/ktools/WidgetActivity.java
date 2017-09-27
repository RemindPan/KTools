package com.jiangkang.ktools;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.jiangkang.ktools.widget.SearchViewActivity;
import com.jiangkang.tools.utils.ToastUtils;
import com.jiangkang.tools.widget.FloatingWindow;
import com.jiangkang.tools.widget.KNotification;

public class WidgetActivity extends AppCompatActivity {

  @BindView(R.id.btn_search_view) Button btnSearchView;

  @BindView(R.id.btn_show_floating_window) Button btnShowFloatingWindow;

  @BindView(R.id.btn_dismiss_floating_window) Button btnDismissFloatingWindow;

  @BindView(R.id.btn_set_toast_show_time) Button btnSetToastShowTime;
  @BindView(R.id.btn_create_simple_notification) Button mBtnCreateSimpleNotification;
  @BindView(R.id.btn_show_custom_notification) Button mBtnShowCustomNotification;

  public static void launch(Context context, Bundle bundle) {
    Intent intent = new Intent(context, WidgetActivity.class);
    if (bundle != null) {
      intent.putExtras(bundle);
    }
    context.startActivity(intent);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_widget);
    ButterKnife.bind(this);
  }

  @OnClick(R.id.btn_search_view) public void onBtnSearchViewClicked() {
    SearchViewActivity.launch(this, null);
  }

  @OnClick(R.id.btn_show_floating_window) public void onBtnShowFloatingWindowClicked() {
    FloatingWindow.show(this, "来来来，看这里\n这是一个悬浮框");
  }

  @OnClick(R.id.btn_dismiss_floating_window) public void onBtnDismissFloatingWindowClicked() {
    FloatingWindow.dismiss();
  }

  @OnClick(R.id.btn_set_toast_show_time) public void onBtnSetToastShowTimeClicked() {
    ToastUtils.showToast("测试自定义显示时间Toast:9s", 9000);
  }

  @OnClick(R.id.btn_create_simple_notification) public void onBtnCreateSimpleNotificationClicked() {
    KNotification.createNotification(this,R.mipmap.ic_launcher,"测试标题","测试内容",new Intent(this,MainActivity.class));
  }

  @OnClick(R.id.btn_show_custom_notification) public void onBtnShowCustomNotificationClicked() {
    RemoteViews views = new RemoteViews(this.getPackageName(), R.layout.layout_big_notification);
    views.setImageViewResource(R.id.iv_notification_img, R.drawable.demo);
    KNotification.createNotification(this, R.mipmap.ic_launcher, views,
        new Intent(this, MainActivity.class));
  }

  

}
