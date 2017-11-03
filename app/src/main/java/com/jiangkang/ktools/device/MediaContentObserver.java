package com.jiangkang.ktools.device;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;

import com.jiangkang.tools.utils.LogUtils;
import com.jiangkang.tools.widget.KDialog;


/**
 * Created by jiangkang on 2017/11/3.
 * description：图片内容变化监听
 */

public class MediaContentObserver extends ContentObserver{


    private static final String[] FILENAME_FILTER = new String[]{
            "screenshot", "screen_shot", "screen-shot", "screen shot",
            "screencapture", "screen_capture", "screen-capture", "screen capture",
            "screencap", "screen_cap", "screen-cap", "screen cap"
    };

    private static final String[] MEDIA_PROJECTIONS = new String[]{
            MediaStore.Images.ImageColumns.DATA,
            MediaStore.Images.ImageColumns.DATE_TAKEN,
    };


    private static final String MEDIA_SORT_ORDER = MediaStore.Images.ImageColumns.DATE_ADDED + " desc limit 1";
    private static final String TAG = "MediaContentObserver";

    private Uri mContentUri;

    private Context mContext;

    public MediaContentObserver(Context context, Handler handler, Uri mContentUri) {
        super(handler);
        this.mContentUri = mContentUri;
        this.mContext = context;
    }


    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        handleChange(mContentUri);
    }

    private void handleChange(Uri uri) {

        ContentResolver resolver = mContext.getContentResolver();

        Cursor cursor = resolver.query(
                uri,
                MEDIA_PROJECTIONS,
                null,
                null,
                MEDIA_SORT_ORDER);

        if (cursor != null && cursor.moveToFirst()){
            Log.d(TAG, "handleChange: cusor count = " + cursor.getCount());
            int dataIndex = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            int dataTakenIndex = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATE_TAKEN);
            String data = cursor.getString(dataIndex);
            long dataTaken = cursor.getLong(dataTakenIndex);

            handleTheLatestRowData(data,dataTaken);
        }


    }

    private void handleTheLatestRowData(String data, long dataTaken) {
        Log.d(TAG, "handleTheLatestRowData: \n" + "data = " + data + "\ndataToken = " + dataTaken);
        if (isScreenShot(data,dataTaken)){
            Log.d(TAG, "handleTheLatestRowData: data = " + data + "\ntime = " + dataTaken);
            Bitmap bitmap = BitmapFactory.decodeFile(data);
            KDialog.showImgInDialog(mContext,bitmap);
        }else {
            //not
        }

    }

    private boolean isScreenShot(String data, long dataTaken) {
        data = data.toLowerCase();
        for (String keyword : FILENAME_FILTER){
            if (data.contains(keyword)){
                return true;
            }
        }
        return false;
    }
}
