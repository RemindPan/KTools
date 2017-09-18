package com.jiangkang.ktools;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.jiangkang.tools.widget.KDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RequestsActivity extends AppCompatActivity {

    @BindView(R.id.btn_get_a_url)
    Button btnGetAUrl;
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
}
