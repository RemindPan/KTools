package com.jiangkang.ktools;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.jiangkang.tools.system.ContactHelper;
import com.jiangkang.tools.system.ContactsLoaderCallback;
import com.jiangkang.tools.utils.ClipboardUtils;
import com.jiangkang.tools.utils.ToastUtils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jiangkang on 2017/9/5.
 * description：
 */

public class SystemActivity extends AppCompatActivity {

    private static final int REQUEST_PERMISSION_READ_CONTACTS = 1001;
    private static final String TAG = SystemActivity.class.getSimpleName();

    @BindView(R.id.btn_open_contacts)
    Button mBtnOpenContacts;

    @BindView(R.id.btn_get_all_contacts)
    Button btnGetAllContacts;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.btn_set_clipboard)
    Button btnSetClipboard;
    @BindView(R.id.btn_get_clipboard)
    Button btnGetClipboard;

    private JSONObject jsonObject;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system);
        ButterKnife.bind(this);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @OnClick(R.id.btn_open_contacts)
    public void onOpenContactsClicked() {

        ContactHelper helper = new ContactHelper(this);
        helper.queryContactNameList();

//        if (checkSelfPermission(Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
//            //granted
//            gotoContactPage();
//        } else {
//            //not granted
//            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_PERMISSION_READ_CONTACTS);
//        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                gotoContactPage();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void gotoContactPage() {
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent, 1111);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1111) {
            if (resultCode == Activity.RESULT_OK) {
                if (data == null) {
                    Log.d(TAG, "onActivityResult: 什么东西都没有选");
                } else {
                    Log.d(TAG, "onActivityResult: 有返回");
                    Uri contactData = data.getData();

                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @OnClick(R.id.btn_get_all_contacts)
    public void onBtnGetAllContactsClicked() {
        LoaderManager.enableDebugLogging(true);
        final ContactHelper helper = new ContactHelper(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                jsonObject = helper.queryContactList();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new AlertDialog.Builder(SystemActivity.this)
                                .setTitle("通讯录")
                                .setMessage(jsonObject.toString())
                                .setPositiveButton("关闭", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .show();
                    }
                });
            }
        }).start();


//        ContactsActivity.launch(this,null);
//        ContactsLoaderCallback callback = new ContactsLoaderCallback(this);
//        callback.setQueryListener(new ContactsLoaderCallback.QueryListener() {
//            @Override
//            public void success(final JSONObject object) {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        new AlertDialog.Builder(SystemActivity.this)
//                                .setTitle("通讯录")
//                                .setMessage(object.toString())
//                                .show();
//                    }
//                });
//            }
//        });
//        getLoaderManager().initLoader(1111,null,callback);
    }



    @OnClick(R.id.btn_set_clipboard)
    public void onBtnSetClipboardClicked() {
        String content = etContent.getText().toString();
        ClipboardUtils.putStringToClipboard(content);
        ToastUtils.showShortToast("设置成功");
    }

    @OnClick(R.id.btn_get_clipboard)
    public void onBtnGetClipboardClicked() {
        ToastUtils.showShortToast(ClipboardUtils.getStringFromClipboard());
    }
}
