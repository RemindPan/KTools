package com.jiangkang.ktools.audio;

import com.jiangkang.ktools.R;

import java.util.HashMap;

/**
 * Created by jiangkang on 2017/9/27.
 */

public class WordVoiceMap {

    private static HashMap<String,Integer> voiceMap = new HashMap<>();

    static {
        voiceMap.put("0", R.raw.tts_0);
        voiceMap.put("1",R.raw.tts_1);
        voiceMap.put("2",R.raw.tts_2);
        voiceMap.put("3",R.raw.tts_3);
        voiceMap.put("万",R.raw.tts_ten_thousand);
        voiceMap.put("十",R.raw.tts_ten);
        voiceMap.put("元",R.raw.tts_yuan);
    }


    public static int getVoiceId(String content){
        if (voiceMap.containsKey(content)){
            return voiceMap.get(content);
        }
        return -1;
    }


}
