package com.jiangkang.ktools;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.jiangkang.ktools.audio.MoneyConvert;
import com.jiangkang.ktools.audio.VoiceSpeaker;
import com.jiangkang.ktools.audio.VoiceTemplate;
import com.jiangkang.tools.utils.FileUtils;
import com.jiangkang.tools.utils.ToastUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AudioActivity extends AppCompatActivity {

    private static final String TAG = "AudioActivity";

    @BindView(R.id.et_text_content)
    EditText etTextContent;

    @BindView(R.id.btn_text_to_speech)
    Button btnTextToSpeech;

    @BindView(R.id.btn_play_single_sound)
    Button btnPlaySingleSound;

    @BindView(R.id.btn_play_multi_sounds)
    Button btnPlayMultiSounds;

    private TextToSpeech.OnInitListener onInitListener;

    private TextToSpeech speech;


    private MediaPlayer.OnCompletionListener onCompletionListener;

    private MediaPlayer player = new MediaPlayer();

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
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (speech != null) {
            speech.shutdown();
        }
    }

    @OnClick(R.id.btn_text_to_speech)
    public void onBtnTextToSpeechClicked() {
        onInitListener = new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = speech.setLanguage(Locale.ENGLISH);
                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        ToastUtils.showShortToast("语言不支持");
                    } else {
                        String content = "This is a default voice";
                        if (!TextUtils.isEmpty(etTextContent.getText().toString())) {
                            content = etTextContent.getText().toString();
                        }
                        speech.speak(content, TextToSpeech.QUEUE_FLUSH, null);
                    }
                }
            }
        };
        speech = new TextToSpeech(this, onInitListener);
    }

    @OnClick(R.id.btn_play_single_sound)
    public void onBtnPlaySingleSoundClicked() {
        MediaPlayer player = MediaPlayer.create(this, R.raw.tts_success);
        player.start();
    }

    @OnClick(R.id.btn_play_multi_sounds)
    public void onBtnPlayMultiSoundsClicked() {

        String numString = "697834214.23";

//        String content = MoneyConvert.arabNumToChineseRMB(108);
//        ToastUtils.showShortToast(content);
//
        List<String> list = new VoiceTemplate()
                .setPrefix("success")
                .setNumString(numString)
                .setSuffix("voucher")
                .gen();

        List<String> secondList = new VoiceTemplate()
                .setPrefix("koubei_daibo")
                .setNumString(numString)
                .setSuffix("yuan")
                .gen();

        List<String> defaultList = VoiceTemplate.defalut.setNumString(numString).setSuffix("yuan").gen();

        VoiceSpeaker.getInstance().speak(list);
        VoiceSpeaker.getInstance().speak(secondList);
        VoiceSpeaker.getInstance().speak(defaultList);

    }

}
