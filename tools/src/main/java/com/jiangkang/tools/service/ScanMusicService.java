package com.jiangkang.tools.service;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.annotation.Nullable;

import com.jiangkang.tools.bean.Song;
import com.jiangkang.tools.utils.LogUtils;
import com.jiangkang.tools.utils.ToastUtils;

import java.util.ArrayList;

/**
 * Created by jiangkang on 2017/10/31.
 * description：扫描音乐的Service
 */

public class ScanMusicService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();

        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<Song> songs = scanSongs(ScanMusicService.this);
                LogUtils.d(songs);
                ToastUtils.showLongToast(songs.toString());
            }
        }).start();

    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }


    private ArrayList<Song> scanSongs(Context context) {
        ArrayList<Song> result = new ArrayList<>();
        ContentResolver resolver = context.getContentResolver();
        Cursor cursor = resolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                null,
                null,
                null,
                null);
        if (cursor != null && !cursor.isClosed() && cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                String songName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                String location = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));

                Song song = new Song();
                song.setName(songName)
                        .setArtist(artist)
                        .setLocation(location);
                LogUtils.d(song.toString());
                result.add(song);
            }
        }
        return result;
    }


}
