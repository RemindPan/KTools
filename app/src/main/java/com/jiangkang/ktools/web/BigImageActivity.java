package com.jiangkang.ktools.web;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.jiangkang.ktools.GlideApp;
import com.jiangkang.ktools.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BigImageActivity extends Activity {


    public static void launch(Context context,Bundle data){
        Intent intent = new Intent(context,BigImageActivity.class);
        if (data != null && data.containsKey("imgUrl")){
            String imgUrl = data.getString("imgUrl");
            Bundle bundle = new Bundle();
            bundle.putString("imgUrl",imgUrl);
            intent.putExtras(bundle);
            context.startActivity(intent);
            ((Activity)context).overridePendingTransition(R.anim.anim_activity_center_in,R.anim.anim_activity_center_out);
        }
    }


    private String mImgUrl;


    @BindView(R.id.iv_content)
    ImageView mIvContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_big_image);
        ButterKnife.bind(this);

        if (getIntent() != null){
            Bundle bundle = getIntent().getExtras();
            if (bundle != null && bundle.containsKey("imgUrl")){
                mImgUrl = bundle.getString("imgUrl");

                GlideApp.with(this)
                        .load(mImgUrl)
                        .centerInside()
                        .into(mIvContent);
            }
        }

    }
}
