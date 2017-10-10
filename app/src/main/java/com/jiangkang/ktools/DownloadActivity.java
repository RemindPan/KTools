package com.jiangkang.ktools;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.jiangkang.ktools.download.DownloadInterceptor;
import com.jiangkang.ktools.download.DownloadListener;
import com.jiangkang.ktools.download.FileDownloadService;
import com.jiangkang.tools.permission.RxPermissions;
import com.jiangkang.tools.utils.ToastUtils;
import com.jiangkang.tools.widget.KDialog;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class DownloadActivity extends AppCompatActivity {

    private static final String TAG = "DownloadActivity";
    private final String DOWNLOAD_URL = "https://b-ssl.duitang.com/uploads/item/201308/01/20130801112648_cFtsV.jpeg";

    private final String APK_URL = "https://t.alipayobjects.com/L1/71/100/and/alipay_wap_main.apk";

    @BindView(R.id.btn_download_by_download_manager)
    Button btnDownloadByDownloadManager;
    @BindView(R.id.btn_download_by_retrofit)
    Button btnDownloadByRetrofit;

    private long downloadId;

    RxPermissions rxPermissions;

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

    }

    private void downloadFileByRetrofit() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new DownloadInterceptor(new DownloadListener() {
                    @Override
                    public void onDownloading(long bytesRead, long contentLength) {
                        final int progress = (int) (bytesRead * 100 / contentLength );
                        Log.d(TAG,"progress = " + progress);
                        new Handler(Looper.getMainLooper())
                                .post(new Runnable() {
                                    @Override
                                    public void run() {
                                        KDialog.showProgressDialog(DownloadActivity.this,progress);
                                    }
                                });

                    }

                    @Override
                    public void downloadSuccess() {
                        ToastUtils.showShortToast("下载完成");
                    }
                }))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("http://jiangkang.github.io")
                .client(client)
                .build();

        FileDownloadService service = retrofit.create(FileDownloadService.class);

        service.downloadFile(APK_URL)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        storeFile(responseBody.bytes());
                    }
                });

    }

    private void storeFile(byte[] bytes) {
        File file = new File(getExternalCacheDir(), "支付宝.apk");
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        if (file.exists()) {
            file.exists();
        }
        try {
            file.createNewFile();
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
            bos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            ToastUtils.showShortToast(file.getAbsolutePath());
            try {
                if (fos != null) {
                    fos.close();
                }
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
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
