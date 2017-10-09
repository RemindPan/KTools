package com.jiangkang.ktools;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.jiangkang.tools.permission.RxPermissions;
import com.jiangkang.tools.utils.ToastUtils;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class DownloadActivity extends AppCompatActivity {

    private final String DOWNLOAD_URL = "https://b-ssl.duitang.com/uploads/item/201308/01/20130801112648_cFtsV.jpeg";

    private final String APK_URL = "https://t.alipayobjects.com/L1/71/100/and/alipay_wap_main.apk";

    @BindView(R.id.btn_download_by_download_manager)
    Button btnDownloadByDownloadManager;

    private long downloadId;

    RxPermissions rxPermissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        ButterKnife.bind(this);
        rxPermissions = new RxPermissions(this);
    }

    private void download() {
        DownloadManager manager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(APK_URL));
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"支付宝.apk");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setTitle("通过DownloadManager下载APK");
        request.setDescription("简单的演示一下DownloadManager的使用方法");
        request.allowScanningByMediaScanner();

        downloadId = manager.enqueue(request);

    }


    @TargetApi(Build.VERSION_CODES.M)
    @OnClick(R.id.btn_download_by_download_manager)
    public void onBtnDownloadByDownloadManagerClicked() {
        rxPermissions.request(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE})
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) {
                        if (aBoolean){
                            download();
                        }else {
                            ToastUtils.showShortToast("权限被拒绝！");
                        }
                    }
                });
    }
}
