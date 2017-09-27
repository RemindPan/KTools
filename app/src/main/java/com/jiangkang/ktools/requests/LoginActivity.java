package com.jiangkang.ktools.requests;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.jiangkang.ktools.R;
import com.jiangkang.tools.struct.JsonGenerator;
import com.jiangkang.tools.utils.ToastUtils;
import com.jiangkang.tools.widget.KDialog;
import com.readystatesoftware.chuck.ChuckInterceptor;

import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {


    @BindView(R.id.et_github_username)
    EditText etGithubUsername;

    @BindView(R.id.et_github_password)
    EditText etGithubPassword;

    @BindView(R.id.btn_login_github)
    Button btnLoginGithub;

    public static void launch(Context context, Bundle bundle) {
        Intent intent = new Intent(context, LoginActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Github登录");
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_login_github)
    public void onBtnLoginGithubClicked() {
        String username = etGithubUsername.getText().toString();
        String password = etGithubPassword.getText().toString();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            ToastUtils.showShortToast("用户名或者密码不能为空！");
            return;
        }

        loginGitHub(username, password);

    }

    private void loginGitHub(String username, String password) {
        final OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new ChuckInterceptor(this))
                .build();

        JSONObject payload = new JsonGenerator()
                .put("username", username)
                .put("password", password)
                .gen();

        RequestBody body = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"),
                payload.toString());

        final Request request = new Request.Builder()
                .url("https://api.github.com/users/jiangkang")
                .post(body)
                .build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = client.newCall(request).execute();
                    KDialog.showMsgDialog(LoginActivity.this,response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}

