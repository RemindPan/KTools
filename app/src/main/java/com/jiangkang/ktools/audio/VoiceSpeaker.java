package com.jiangkang.ktools.audio;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import com.jiangkang.tools.utils.FileUtils;

import java.io.IOException;
import java.util.List;

/**
 * Created by jiangkang on 2017/10/17.
 */

public class VoiceSpeaker {

    private static VoiceSpeaker sInstance;

    private MediaPlayer player;

    private VoiceSpeaker(){
        player = new MediaPlayer();
    }

    public static VoiceSpeaker getInstance(){
        if (sInstance == null){
         sInstance = new VoiceSpeaker();
        }
        return sInstance;
    }


    public void speak(final List<String> list){
        if (list != null && list.size() > 0) {
            final int[] counter = {0};
            String path = String.format("sound/tts_%s.mp3", list.get(counter[0]));
            try {
                AssetFileDescriptor fd = FileUtils.getAssetFileDescription(path);
                if (player.isPlaying()) return;
                //custom here to speak the latest message only
                if (player != null){
                    player.reset();
                }
                player.setDataSource(fd.getFileDescriptor(), fd.getStartOffset(),
                        fd.getLength());
                player.prepare();
                player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.start();
                    }
                });
                player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.reset();
                        counter[0]++;
                        if (counter[0] < list.size()) {
                            try {
                                AssetFileDescriptor fileDescriptor = FileUtils.getAssetFileDescription(String.format("sound/tts_%s.mp3", list.get(counter[0])));
                                mp.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(), fileDescriptor.getLength());
                                mp.prepare();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            mp.reset();
                        }
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }



}
