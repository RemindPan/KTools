package com.jiangkang.tools.system;

import android.app.Activity;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.util.Log;

import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.CountDownLatch;


/**
 * Created by jiangkang on 2017/9/8.
 */

public class ContactHelper {

    private static final String TAG = "ContactHelper";
    private Activity context;

    private JSONObject contacts;

    public ContactHelper(Activity context) {
        this.context = context;
    }

    public JSONObject queryContactList(){
        final CountDownLatch latch = new CountDownLatch(1);
        ContactsLoaderCallback callback = new ContactsLoaderCallback(context);
        callback.setQueryListener(new ContactsLoaderCallback.QueryListener() {
            @Override
            public void success(final JSONObject object) {
                contacts = object;
                latch.countDown();
            }
        });
        context.getLoaderManager().restartLoader(0,null,callback);

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return contacts;
    }


    public void queryContactNameList(){

        Cursor cursor = context.getContentResolver()
                .query(
                        ContactsContract.Contacts.CONTENT_URI,
                        new String[]{
                                ContactsContract.Contacts.DISPLAY_NAME
                        },
                        null,
                        null,
                        null
                );

        if (cursor != null && cursor.moveToFirst()){
            do {
                int nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
                String name = cursor.getString(nameIndex);
                Log.d(TAG, "queryContactNameList: \n name = " + name);
            }while (cursor.moveToNext());

        }

    }

}
