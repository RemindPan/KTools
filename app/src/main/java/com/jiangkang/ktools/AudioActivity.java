package com.jiangkang.ktools;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jiangkang.tools.utils.ToastUtils;

import java.util.Locale;

public class AudioActivity extends AppCompatActivity {

    private TextToSpeech.OnInitListener onInitListener;

    private TextToSpeech speech;


    public static void launch(Context context, Bundle bundle) {
        Intent intent = new Intent(context, AudioActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);

        initVar();

    }

    private void initVar() {

        onInitListener = new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS){
                    int result = speech.setLanguage(Locale.ENGLISH);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                        ToastUtils.showShortToast("语言不支持");
                    }else {
                        speech.speak("just test",TextToSpeech.QUEUE_FLUSH,null);
                    }

                }
            }
        };

        speech = new TextToSpeech(this,onInitListener);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (speech != null){
            speech.shutdown();
        }
    }
}
