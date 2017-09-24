package com.jiangkang.ktools;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.TextView;
import com.jiangkang.tools.utils.SpUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.jiangkang.tools.utils.ToastUtils;
import com.jiangkang.tools.widget.KDialog;
import java.util.zip.Inflater;

public class StorageActivity extends AppCompatActivity {

    @BindView(R.id.btn_set_pref)
    Button btnSetPref;
    @BindView(R.id.btn_get_pref)
    Button btnGetPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
        ButterKnife.bind(this);
        setTitle("Storage");
    }

    @OnClick(R.id.btn_set_pref)
    public void onBtnSetPrefClicked() {

      final View dialogView = LayoutInflater.from(this)
          .inflate(R.layout.layout_dialog_key_value,null);

      KDialog.showCustomViewDialog(this, "将值存入SharedPreference中",dialogView, new DialogInterface.OnClickListener() {
        @Override public void onClick(DialogInterface dialog, int which) {
          EditText etKey = (EditText) dialogView.findViewById(R.id.et_key);
          EditText etValue = (EditText) dialogView.findViewById(R.id.et_value);

          String key = etKey.getText().toString();
          String value = etValue.getText().toString();

          if (TextUtils.isEmpty(key)){
            ToastUtils.showShortToast("key值不能为空");
            return;
          }

          SpUtils.getInstance(StorageActivity.this,"storage")
              .putString(key,value);

          ToastUtils.showShortToast("存储成功");

          dialog.dismiss();
        }
      }, new DialogInterface.OnClickListener() {
        @Override public void onClick(DialogInterface dialog, int which) {
          dialog.dismiss();
        }
      });

        SpUtils.getInstance(this,"storage")
                .putString("author","姜康");
    }

    @OnClick(R.id.btn_get_pref)
    public void onBtnGetPrefClicked() {

      final View dialogView = LayoutInflater.from(this)
          .inflate(R.layout.layout_dialog_key_value,null);
      final EditText etKey = (EditText) dialogView.findViewById(R.id.et_key);
      EditText etValue = (EditText) dialogView.findViewById(R.id.et_value);
      TextView tvValue = (TextView) dialogView.findViewById(R.id.tv_value);
      tvValue.setVisibility(View.GONE);
      etValue.setVisibility(View.GONE);

      KDialog.showCustomViewDialog(this, "从SharedPreference中取值",dialogView, new DialogInterface.OnClickListener() {
        @Override public void onClick(DialogInterface dialog, int which) {
            String key = etKey.getText().toString();

            if (TextUtils.isEmpty(key)){
              ToastUtils.showShortToast("key值不符合规范");
              return;
            }

            String value = SpUtils.getInstance(StorageActivity.this,"storage")
                .getString(key,"null");

          ToastUtils.showToast(value,9000);
          dialog.dismiss();

        }
      }, new DialogInterface.OnClickListener() {
        @Override public void onClick(DialogInterface dialog, int which) {
          dialog.dismiss();
        }
      });

    }
}
