package com.jiangkang.ktools;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.jiangkang.tools.utils.QRCodeUtils;
import com.jiangkang.tools.utils.ToastUtils;
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
        ToastUtils.showShortToast("暂未实现");
    }

    @OnClick(R.id.btn_gen_qr_code)
    public void onBtnGenQrCodeClicked() {
        if (!TextUtils.isEmpty(etUrl.getText().toString())) {
            Bitmap bitmap = QRCodeUtils.createQRCodeBitmap(
                    etUrl.getText().toString(),
                    640,
                    640
            );
            if (bitmap != null) {
                KDialog.showImgInDialog(this, bitmap);
            }
        }else {
            ToastUtils.showShortToast("请在输入框输入url");
        }

    }
}
