package com.jiangkang.ktools;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

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

    private List<HashMap<String,String>> contacts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system);
        ButterKnife.bind(this);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @OnClick(R.id.btn_open_contacts)
    public void onOpenContactsClicked() {
        if (checkSelfPermission(Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            //granted
            gotoContactPage();
        } else {
            //not granted
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_PERMISSION_READ_CONTACTS);
        }

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
        Uri uri = Uri.parse("content://contacts/people");
        Intent intent = new Intent(Intent.ACTION_PICK, uri);
        startActivityForResult(intent, 1111);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1111) {
            if (resultCode == Activity.RESULT_OK) {
                if (data == null) {
                    Log.d(TAG, "onActivityResult: 什么东西都没有选");
                } else {

                    getContacts();

                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }



    @OnClick(R.id.btn_get_all_contacts)
    public void onBtnGetAllContactsClicked() {

    }

    public void getContacts() {


    }
}
