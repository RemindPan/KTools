package com.jiangkang.ktools;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.google.zxing.EncodeHintType;
import com.jiangkang.tools.utils.QRCodeUtils;
import com.jiangkang.tools.widget.KDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScanActivity extends AppCompatActivity {

    @BindView(R.id.btn_scan_qr)
    Button btnScanQr;
    @BindView(R.id.et_url)
    EditText etUrl;
    @BindView(R.id.btn_gen_qr_code)
    Button btnGenQrCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.btn_scan_qr)
    public void onBtnScanQrClicked() {
//        Intent intent = new Intent(this, EncodeHintType)
    }

    @OnClick(R.id.btn_gen_qr_code)
    public void onBtnGenQrCodeClicked() {
        if (!TextUtils.isEmpty(etUrl.getText())) {
            Bitmap bitmap = QRCodeUtils.createQRCodeBitmap(
                    "https://jiangkang.github.io",
                    640,
                    640
            );
            if (bitmap != null) {
                KDialog.showImgInDialog(this, bitmap);
            }
        }

    }
}
