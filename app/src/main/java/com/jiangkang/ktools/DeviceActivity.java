package com.jiangkang.ktools;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.jiangkang.tools.device.DeviceUtils;
import com.jiangkang.tools.utils.AppUtils;
import com.jiangkang.tools.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class DeviceActivity extends AppCompatActivity {

    @BindView(R.id.btn_check_network_info)
    Button btnCheckNetworkInfo;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);
        unbinder = ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_check_network_info)
    public void onBtnCheckNetworkInfoClicked() {
//        ToastUtils.showShortToast(DeviceUtils.getNetWorkType());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
