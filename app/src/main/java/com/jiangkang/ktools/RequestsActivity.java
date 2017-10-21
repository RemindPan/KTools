package com.jiangkang.ktools;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.google.gson.Gson;
import com.jiangkang.ktools.requests.LoginActivity;
import com.jiangkang.requests.KRequests;
import com.jiangkang.requests.zhihu.ZhihuApi;
import com.jiangkang.requests.zhihu.bean.LatestNews;
import com.jiangkang.requests.zhihu.bean.StartPageInfo;
import com.jiangkang.tools.utils.ToastUtils;
import com.jiangkang.tools.widget.KDialog;

import org.json.JSONException;
import org.json.JSONObject;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.FlowableSubscriber;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RequestsActivity extends AppCompatActivity {

    private static final String TAG = "RequestsActivity";
    @BindView(R.id.btn_get_a_url)
    Button btnGetAUrl;
    @BindView(R.id.btn_post)
    Button btnPost;
    private OkHttpClient client;

    private static final String URL_GET = "https://api.github.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);
        ButterKnife.bind(this);
        setTitle("Requests");

        initVar();

    }

    private void initVar() {
        client = new OkHttpClient();
    }


    private void getAUrl() {
        requestUrl(URL_GET);
    }

    private void requestUrl(final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    String result = response.body().string();
                    KDialog.showMsgDialog(RequestsActivity.this, new JSONObject(result).toString(4));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }


    @OnClick(R.id.btn_get_a_url)
    public void onBtnGetAUrlClicked() {
        getAUrl();
    }

    @OnClick(R.id.btn_post)
    public void onBtnPostClicked() {
        KRequests.request(ZhihuApi.class).getLatestNews()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LatestNews>() {
                    @Override
                    public void accept(LatestNews latestNews) throws Exception {
                        KDialog.showMsgDialog(RequestsActivity.this,new Gson().toJson(latestNews));
                    }
                });
    }
}
