package com.jiangkang.ktools;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SecurityActivity extends AppCompatActivity {


    @BindView(R.id.et_original)
    EditText etOriginal;

    @BindView(R.id.et_key)
    EditText etKey;

    @BindView(R.id.et_result)
    EditText tvResult;

    @BindView(R.id.btn_base64_encode)
    Button btnBase64Encode;

    @BindView(R.id.btn_base64_decode)
    Button btnBase64Decode;

    @BindView(R.id.layout_key)
    LinearLayout layoutKey;

    public static void launch(Context context, Bundle bundle) {
        Intent intent = new Intent(context, SecurityActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.btn_base64_encode)
    public void onBtnBase64EncodeClicked() {
        String inputString = etOriginal.getText().toString();
        String result = Base64.encodeToString(inputString.getBytes(),Base64.DEFAULT);
        tvResult.setText(result);
    }

    @OnClick(R.id.btn_base64_decode)
    public void onBtnBase64DecodeClicked() {
        String inputString = etOriginal.getText().toString();
        byte[] resultByte = Base64.decode(inputString,Base64.DEFAULT);
        String result = new String(resultByte);
        tvResult.setText(result);
    }



}
