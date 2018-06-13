package com.jiangkang.ktools;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.jiangkang.ktools.download.DownloadListener;
import com.jiangkang.ktools.download.DownloadUtils;
import com.jiangkang.tools.permission.RxPermissions;
import com.jiangkang.tools.utils.ToastUtils;
import com.jiangkang.tools.widget.KDialog;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class DownloadActivity extends AppCompatActivity {

    private static final String TAG = "DownloadActivity";
    private final String DOWNLOAD_URL = "https://b-ssl.duitang.com/uploads/item/201308/01/20130801112648_cFtsV.jpeg";

    private final String APK_URL = "http://bestpay.ctdns.net/bestpay_common_signed.apk";

    @BindView(R.id.btn_download_by_download_manager)
    Button btnDownloadByDownloadManager;
    @BindView(R.id.btn_download_by_retrofit)
    Button btnDownloadByRetrofit;

    RxPermissions rxPermissions;

    private long downloadId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        ButterKnife.bind(this);
        rxPermissions = new RxPermissions(this);
    }

    private void downloadByDownloadManager() {
        DownloadManager manager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(APK_URL));
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "支付宝.apk");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setTitle("通过DownloadManager下载APK");
        request.setDescription("简单的演示一下DownloadManager的使用方法");
        request.allowScanningByMediaScanner();
        downloadId = manager.enqueue(request);

        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                if (id == downloadId) {
                    ToastUtils.showShortToast("下载完成");

                }
            }
        }, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    private void downloadFileByRetrofit() {
        DownloadUtils.getInstance()
                .downloadUrl(APK_URL)
                .downloadFilePath(Environment.getExternalStorageDirectory() + File.separator + "ktools", "翼支付.apk")
                .setDownloadListener(new DownloadListener() {
                    @Override
                    public void onDownloading(final int progress, long contentLength) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                KDialog.showProgressDialog(DownloadActivity.this, progress);
                            }
                        });
                    }

                    @Override
                    public void onSuccess() {
                        ToastUtils.showShortToast("下载完成");
                    }

                    @Override
                    public void onError(String message) {
                        ToastUtils.showShortToast("下载失败");
                    }
                })
                .start();
//                .startByMultiThread(4);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @OnClick(R.id.btn_download_by_download_manager)
    public void onBtnDownloadByDownloadManagerClicked() {
        rxPermissions.request(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE})
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) {
                        if (aBoolean) {
                            downloadByDownloadManager();
                        } else {
                            ToastUtils.showShortToast("权限被拒绝！");
                        }
                    }
                });
    }

    @OnClick(R.id.btn_download_by_retrofit)
    public void onBtnDownloadByRetrofitClicked() {
        rxPermissions.request(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE})
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) {
                        if (aBoolean) {
                            downloadFileByRetrofit();
                        } else {
                            ToastUtils.showShortToast("权限被拒绝！");
                        }
                    }
                });
    }









}
