package com.jiangkang.ktools;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.jiangkang.tools.device.DeviceUtils;
import com.jiangkang.tools.utils.AppUtils;
import com.jiangkang.tools.utils.ToastUtils;

import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class DeviceActivity extends AppCompatActivity {

    private static final String TAG = DeviceActivity.class.getSimpleName();
    @BindView(R.id.btn_check_network_info)
    Button btnCheckNetworkInfo;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);
        unbinder = ButterKnife.bind(this);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @OnClick(R.id.btn_check_network_info)
    public void onBtnCheckNetworkInfoClicked() {
//        ToastUtils.showShortToast(String.valueOf(DeviceUtils.getMobileSignalStrength()));
//        ToastUtils.showShortToast(DeviceUtils.getNetWorkType());
        if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            showMsgDialog("信号强度", String.valueOf(DeviceUtils.getMobileSignalStrength()));
        }else {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},1111);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void showMsgDialog(String title, String content){
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(content)
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1111){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                showMsgDialog("信号强度", String.valueOf(DeviceUtils.getMobileSignalStrength()));
            }
        }
    }
}
