package com.jiangkang.ktools;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.jiangkang.tools.utils.AppUtils;
import com.jiangkang.tools.utils.NetworkUtils;
import com.jiangkang.tools.widget.KDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class DeviceActivity extends AppCompatActivity {

  private static final String TAG = DeviceActivity.class.getSimpleName();
  @BindView(R.id.btn_check_network_info) Button btnCheckNetworkInfo;
  @BindView(R.id.btn_check_current_activity) Button btnCheckCurrentActivity;

  private Unbinder unbinder;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_device);
    setTitle("Device");
    unbinder = ButterKnife.bind(this);
  }

  @TargetApi(Build.VERSION_CODES.M) @OnClick(R.id.btn_check_network_info)
  public void onBtnCheckNetworkInfoClicked() {
    StringBuilder builder = new StringBuilder();
    builder.append(String.format("网络类型: %s\n", NetworkUtils.getNetWorkType()))
        .append(String.format("Mac地址: %s\n", NetworkUtils.getMacAddress()));
    KDialog.showMsgDialog(this, builder.toString());
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    unbinder.unbind();
  }

  private void showMsgDialog(String title, String content) {
    new AlertDialog.Builder(this).setTitle(title).setMessage(content).show();
  }

  @Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
  }

  @OnClick(R.id.btn_check_current_activity) public void onBtnCheckCurrentActivityClicked() {
    KDialog.showMsgDialog(this, AppUtils.getCurrentActivity());
  }

  private void queryRunningService() {
    ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
    List<ActivityManager.RunningServiceInfo> serviceInfoList =
        manager.getRunningServices(Integer.MAX_VALUE);

    for (ActivityManager.RunningServiceInfo serviceInfo : serviceInfoList) {
      Log.d(TAG, "queryRunningService: \n"
          + "process:"
          + serviceInfo.process
          + "\n---service:"
          + serviceInfo.service.flattenToString()
          + "\n---pid:"
          + serviceInfo.pid
          + "\n---uid:"
          + serviceInfo.uid
          + "\n---clientPackage:"
          + serviceInfo.clientPackage
          + "\n---lastActivityTime:"
          + serviceInfo.lastActivityTime);
    }
  }

  private void queryRunningActivityies() {
    ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
    List<ActivityManager.RunningAppProcessInfo> appProcessInfoList =
        manager.getRunningAppProcesses();
    for (ActivityManager.RunningAppProcessInfo appProcessInfo : appProcessInfoList) {
      Log.d(TAG, "queryRunningActivityies: \n"
          + "process:"
          + appProcessInfo.processName
          + "\n---pkgList:"
          + appProcessInfo.pkgList.length
          + "\n---componentName:"
          + appProcessInfo.importanceReasonComponent);
    }
  }
}
