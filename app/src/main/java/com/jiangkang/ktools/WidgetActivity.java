package com.jiangkang.ktools;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.ScrollView;

import com.jiangkang.ktools.widget.BottomNavigationActivity;
import com.jiangkang.ktools.widget.CoordinatorActivity;
import com.jiangkang.ktools.widget.FabActivity;
import com.jiangkang.ktools.widget.KDialogActivity;
import com.jiangkang.ktools.widget.ScrollingActivity;
import com.jiangkang.tools.utils.ToastUtils;
import com.jiangkang.tools.widget.FloatingWindow;
import com.jiangkang.tools.widget.KDialog;
import com.jiangkang.tools.widget.KNotification;
import com.jiangkang.widget.ui.UpdateDialogActivity;
import com.jiangkang.widget.view.TaiChiView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WidgetActivity extends AppCompatActivity {

    @BindView(R.id.btn_coordinator_layout)
    Button btnCoordinatorLayout;

    @BindView(R.id.btn_show_floating_window)
    Button btnShowFloatingWindow;

    @BindView(R.id.btn_dismiss_floating_window)
    Button btnDismissFloatingWindow;

    @BindView(R.id.btn_set_toast_show_time)
    Button btnSetToastShowTime;

    @BindView(R.id.btn_create_simple_notification)
    Button mBtnCreateSimpleNotification;

    @BindView(R.id.btn_show_custom_notification)
    Button mBtnShowCustomNotification;

    @BindView(R.id.btn_widget_dialog)
    Button btnWidgetDialog;

    @BindView(R.id.layout_content)
    ScrollView layoutContent;
    @BindView(R.id.btn_floating_action_button)
    Button mBtnFloatingActionButton;
    @BindView(R.id.btn_scroll)
    Button btnScroll;
    @BindView(R.id.btn_bottom_nav)
    Button btnBottomNav;

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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //转场动画
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            getWindow().setEnterTransition(new Fade());
            getWindow().setExitTransition(new Fade());
        }

        setContentView(R.layout.activity_widget);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_coordinator_layout)
    public void onBtnSearchViewClicked() {
        CoordinatorActivity.launch(this, null);
//        SearchViewActivity.launch(this, null);
    }

    @OnClick(R.id.btn_show_floating_window)
    public void onBtnShowFloatingWindowClicked() {
        FloatingWindow.show(this, "来来来，看这里\n这是一个悬浮框");
    }

    @OnClick(R.id.btn_dismiss_floating_window)
    public void onBtnDismissFloatingWindowClicked() {
        FloatingWindow.dismiss();
    }

    @OnClick(R.id.btn_set_toast_show_time)
    public void onBtnSetToastShowTimeClicked() {
        ToastUtils.showToast("测试自定义显示时间Toast:9s", 9000);
    }

    @OnClick(R.id.btn_create_simple_notification)
    public void onBtnCreateSimpleNotificationClicked() {
        KNotification.createNotification(this, R.mipmap.ic_launcher, "测试标题", "测试内容", new Intent(this, MainActivity.class));
    }

    @OnClick(R.id.btn_show_custom_notification)
    public void onBtnShowCustomNotificationClicked() {
        RemoteViews views = new RemoteViews(this.getPackageName(), R.layout.layout_big_notification);
        views.setImageViewResource(R.id.iv_notification_img, R.drawable.demo);
        KNotification.createNotification(this, R.mipmap.ic_launcher, views,
                new Intent(this, MainActivity.class));
    }


    @OnClick(R.id.btn_widget_dialog)
    public void onBtnWidgetDialogClicked() {
        KDialogActivity.launch(this, null);
    }

    @OnClick(R.id.btn_floating_action_button)
    public void onFabClicked() {
        FabActivity.launch(this, null);
    }

    @OnClick(R.id.btn_scroll)
    public void onBtnScrollClicked() {
        ScrollingActivity.launch(this, null);
    }

    @OnClick(R.id.btn_bottom_nav)
    public void onBottomNavClicked() {
        BottomNavigationActivity.launch(this, null);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        }
    }
}
