package com.jiangkang.ktools.download;

import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;

import com.jiangkang.tools.utils.FileUtils;
import com.jiangkang.tools.utils.ToastUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executors;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by jiangkang on 2017/12/5.
 * description：文件下载工具类
 */

public class DownloadUtils {

    private static final String TAG = "DownloadUtils";

    private Retrofit mRetrofit;

    private OkHttpClient mOkhttpClient;

    private FileDownloadService mDownloadService;

    private File mDownloadFile;

    private String mDownloadUrl;

    private DownloadListener mDownloadListener;

    private DownloadUtils() {

    }

    public synchronized static DownloadUtils getInstance() {
        return new DownloadUtils();
    }


    public DownloadUtils setDownloadListener(@NonNull DownloadListener listener) {
        mDownloadListener = listener;
        return this;
    }


    public DownloadUtils downloadUrl(@NonNull String url) {
        mDownloadUrl = url;
        return this;
    }


    public DownloadUtils downloadFilePath(String parent, @NonNull String child) {
        return downloadFilePath(new File(parent, child));

    }

    public DownloadUtils downloadFilePath(File parentDir, String child) {
        return downloadFilePath(new File(parentDir, child));
    }

    public DownloadUtils downloadFilePath(String pathName) {
        return downloadFilePath(new File(pathName));
    }

    public DownloadUtils downloadFilePath(File file) {
        mDownloadFile = file;
        return this;
    }

    public void start() {
        mOkhttpClient = new OkHttpClient.Builder()
                .addInterceptor(new DownloadInterceptor(mDownloadListener))
                .build();

        mRetrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://jiangkang.github.io")
                .client(mOkhttpClient)
                .build();

        mDownloadService = mRetrofit.create(FileDownloadService.class);

        mDownloadService.downloadFile(mDownloadUrl)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        storeFile(responseBody.bytes());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (mDownloadListener != null) {
                            mDownloadListener.onError(throwable.getMessage());
                        }
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        if (mDownloadListener != null) {
                            mDownloadListener.onSuccess();
                        }
                    }
                });
    }


    public void startByMultiThread(final int threadCount) {
        mOkhttpClient = new OkHttpClient.Builder()
                .addInterceptor(new DownloadInterceptor(mDownloadListener))
                .build();

        mRetrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://jiangkang.github.io")
                .client(mOkhttpClient)
                .build();

        mDownloadService = mRetrofit.create(FileDownloadService.class);

        new Thread(new Runnable() {
            @Override
            public void run() {
                final URL url;
                try {
                    url = new URL(mDownloadUrl);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.connect();
                    long totalSize = connection.getContentLength();
                    connection.disconnect();
                    final long blockSize = totalSize / threadCount;
                    final RandomAccessFile file = new RandomAccessFile(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "ttt.apk", "rwd");
                    file.setLength(totalSize);
                    file.close();
                    for (int i = 0; i < threadCount; i++) {
                        final int finalI = i + 1;
                        final long startIndex = (finalI - 1) * blockSize;
                        final long endIndex;
                        if (i == threadCount - 1) {
                            endIndex = totalSize;
                        } else {
                            endIndex = (finalI * blockSize) - 1;
                        }
                        Executors.newFixedThreadPool(threadCount).submit(new Runnable() {
                            @Override
                            public void run() {
                                Log.d("DownloadUtils", "run: 正在下载：" + finalI);
                                mDownloadService.downloadFile(mDownloadUrl, String.format("bytes=%s-%s", startIndex, endIndex))
                                        .observeOn(Schedulers.io())
                                        .subscribeOn(Schedulers.io())
                                        .subscribe(new Consumer<ResponseBody>() {
                                            @Override
                                            public void accept(ResponseBody responseBody) throws Exception {
                                                Log.d(TAG, "accept: id = " + finalI + " size = " + responseBody.contentLength()/(1024 * 1024));
                                                InputStream is = responseBody.byteStream();
                                                RandomAccessFile targetFile = new RandomAccessFile(mDownloadFile, "rwd");
                                                targetFile.seek(startIndex);
                                                int length = 0;
                                                byte[] buffer = new byte[1024 * 4];
                                                while ((length = is.read(buffer)) != -1) {
                                                    targetFile.write(buffer, 0, length);
                                                }
                                                is.close();
                                                targetFile.close();
                                            }
                                        }, new Consumer<Throwable>() {
                                            @Override
                                            public void accept(Throwable throwable) throws Exception {
                                                Log.d("DownloadUtils", throwable.getMessage());
                                            }
                                        });
                            }
                        });
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }


    private void storeFile(byte[] bytes) {
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        if (mDownloadFile.exists()) {
            mDownloadFile.delete();
        }
        try {
            mDownloadFile.createNewFile();
            fos = new FileOutputStream(mDownloadFile);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
            bos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            ToastUtils.showShortToast(mDownloadFile.getAbsolutePath());
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


}
