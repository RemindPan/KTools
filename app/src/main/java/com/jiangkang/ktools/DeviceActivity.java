package com.jiangkang.ktools;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.alibaba.android.arouter.launcher.ARouter;
import com.jiangkang.tools.device.DeviceUtils;
import com.jiangkang.tools.permission.RxPermissions;
import com.jiangkang.tools.utils.AppUtils;
import com.jiangkang.tools.utils.FileUtils;
import com.jiangkang.tools.utils.NetworkUtils;
import com.jiangkang.tools.utils.ShellUtils;
import com.jiangkang.tools.utils.ToastUtils;
import com.jiangkang.tools.widget.KDialog;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class DeviceActivity extends AppCompatActivity {

    private static final String TAG = DeviceActivity.class.getSimpleName();

    private static final int WHAT_IP_ADDRESS = 1;
    private static final int WHAT_LOCATION = 2;
    private static final int REQUEST_PERMISSION_LOCATION = 1101;

    @BindView(R.id.btn_check_network_info)
    Button btnCheckNetworkInfo;
    @BindView(R.id.btn_check_current_activity)
    Button btnCheckCurrentActivity;
    @BindView(R.id.btn_adb_wireless)
    Button mBtnAdbWireless;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);
        setTitle("Device");
        unbinder = ButterKnife.bind(this);
        // ATTENTION: This was auto-generated to handle app links.
        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();
    }

    @TargetApi(Build.VERSION_CODES.M)
    @OnClick(R.id.btn_check_network_info)
    public void onBtnCheckNetworkInfoClicked() {

//        StringBuilder builder = new StringBuilder();
//        builder.append(String.format("网络类型: %s\n", NetworkUtils.getNetWorkType()))
//                .append(String.format("Mac地址: %s\n", NetworkUtils.getMacAddress()));
//        KDialog.showMsgDialog(this, builder.toString());

        URL url = getClassLoader().getResource("assets/tt.txt");
//        URL url = this.getClass().getClassLoader().getResource("assets/tt.txt");
        if (url != null) {
            ToastUtils.showShortToast("不为空！" + url.getPath());
        } else {
            ToastUtils.showShortToast("为空");
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


    @OnClick(R.id.btn_check_current_activity)
    public void onBtnCheckCurrentActivityClicked() {
        KDialog.showMsgDialog(this, AppUtils.INSTANCE.getCurrentActivity());
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

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case WHAT_IP_ADDRESS:
                    String ip = DeviceUtils.getIPAddress(DeviceActivity.this);
                    KDialog.showMsgDialog(DeviceActivity.this, "adb connect " + ip);
                    break;
                default:
                    break;
            }

        }
    };

    @OnClick(R.id.btn_adb_wireless)
    public void onAdbWirelessClicked() {
        final String command = "setprop service.adb.tcp.port 5555 && stop adbd && start adbd";
        new Thread(new Runnable() {
            @Override
            public void run() {
                ShellUtils.CommandResult result = ShellUtils.execCmd(command, true);
                handler.sendEmptyMessage(WHAT_IP_ADDRESS);
            }
        }).start();
    }


}
