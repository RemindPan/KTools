package com.jiangkang.ktools;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.widget.Button;

import com.jiangkang.tools.utils.PhoneUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.jiangkang.tools.utils.PhoneUtils.getAllContactInfo;

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


//                    getPhoneContacts(data.getData());
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void getPhoneContacts(Uri data) {
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(data, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            int nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            String name = cursor.getString(nameIndex);
            String phoneNum = "";

            int contactIdIndex = cursor.getColumnIndex(ContactsContract.Contacts._ID);
            String contactId = cursor.getString(contactIdIndex);

            Cursor phoneCursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactId,
                    null,
                    null);

            if (phoneCursor != null) {
                phoneCursor.moveToFirst();
                phoneNum = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            }

            phoneCursor.close();
            cursor.close();

            Log.d(TAG, String.format("getPhoneContacts:\n name = %s \n phoneNum = %s", name, phoneNum));
        }
    }

    @OnClick(R.id.btn_get_all_contacts)
    public void onBtnGetAllContactsClicked() {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                List<HashMap<String, String>> contacts = PhoneUtils.getAllContactInfo();
                for (HashMap<String, String> map : contacts) {
                    for (String key : map.keySet()) {
                        Log.d(TAG, "onBtnGetAllContactsClicked: \nname = " + key + "\nnumber = " + map.get(key));
                    }
                }
            }
        });


    }
}
