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
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.jiangkang.tools.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DownloadActivity extends AppCompatActivity {

    private final String DOWNLOAD_URL = "https://b-ssl.duitang.com/uploads/item/201308/01/20130801112648_cFtsV.jpeg";
    @BindView(R.id.btn_download_by_download_manager)
    Button btnDownloadByDownloadManager;

    private long downloadId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        ButterKnife.bind(this);
    }

    private void download() {
        DownloadManager manager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(DOWNLOAD_URL));
        request.setDestinationInExternalPublicDir("Download", "demo.jpeg");
        request.setTitle("下载Demo");
        request.setDescription("这是一个简单的下载Demo");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        downloadId = manager.enqueue(request);

        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID,-1);
                if (id == downloadId){
                    ToastUtils.showShortToast("下载完成");

                }
            }
        },new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1111) {
            if (grantResults.length > 0) {
                download();
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    @OnClick(R.id.btn_download_by_download_manager)
    public void onBtnDownloadByDownloadManagerClicked() {
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                    1111);
        } else {
            download();
        }
    }
}
