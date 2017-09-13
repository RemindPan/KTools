package com.jiangkang.tools.system;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

import com.jiangkang.tools.struct.JsonGenerator;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by jiangkang on 2017/9/13.
 */

public class ContactsLoaderCallback implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = "ContactsLoaderCallback";

    private JSONObject result;

    private Context context;
    private QueryListener listener;

    public ContactsLoaderCallback(Context context) {
        this.context = context;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        //指定获取_id和display_name两列数据，display_name即为姓名
        String[] projection = new String[]{
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME
        };
        CursorLoader loader = new CursorLoader(
                context,
                ContactsContract.Contacts.CONTENT_URI,
                projection,
                null,
                null,
                null
        );
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, final Cursor data) {

        if (data.isClosed()) {
            return;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                JSONArray jsonArray = new JSONArray();
                if (data != null && data.moveToFirst()) {
                    do {
                        String name = data.getString(
                                data.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
                        );
                        int id = data.getInt(
                                data.getColumnIndex(ContactsContract.Contacts._ID)
                        );

                        //指定获取NUMBER这一列数据
                        String[] phoneProjection = new String[]{
                                ContactsContract.CommonDataKinds.Phone.NUMBER
                        };

                        Cursor cursor = context.getContentResolver().query(
                                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                phoneProjection,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + id,
                                null,
                                null
                        );
                        if (cursor != null && cursor.moveToFirst()) {
                            do {
                                String number = cursor.getString(
                                        cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                                );

                                jsonArray.put(
                                        new JsonGenerator()
                                                .put("name", name)
                                                .put("tel", number)
                                                .gen()
                                );
                            } while (cursor.moveToNext());
                        }

                    } while (data.moveToNext());
                }
                result = new JsonGenerator()
                        .put("list", jsonArray)
                        .gen();

                if (listener != null) {
                    listener.success(result);
                }

                Log.d(TAG, "onLoadFinished: result =\n" + result.toString());
            }
        }).start();

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }


    public void setQueryListener(QueryListener listener) {
        this.listener = listener;
    }

    public interface QueryListener {
        void success(JSONObject object);
    }


}
