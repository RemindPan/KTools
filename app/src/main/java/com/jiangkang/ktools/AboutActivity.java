package com.jiangkang.ktools;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.jiangkang.ktools.web.WebActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutActivity extends AppCompatActivity {

    @BindView(R.id.tv_git)
    TextView tvGit;

    public static void launch(Context context, Bundle bundle) {
        Intent intent = new Intent(context, AboutActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_git)
    public void onTvGitClicked() {
        Bundle bundle = new Bundle();
        bundle.putString("launchUrl","https://github.com/jiangkang/KTools");
        WebActivity.launch(this,bundle);
    }
}
