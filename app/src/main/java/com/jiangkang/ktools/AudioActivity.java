package com.jiangkang.ktools;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

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

        final List<String> list = new ArrayList<>();
        list.add("success");
        list.add("1");
        list.add("ten_thousand");
        list.add("2");
        list.add("thousand");
        list.add("3");
        list.add("hundred");
        list.add("4");
        list.add("ten");
        list.add("5");
        list.add("dot");
        list.add("6");
        list.add("yuan");

        speakChineseNum(list);

    }

    private void speakChineseNum(final List list) {
        final int[] counter = {0};
        String path = String.format("sound/tts_%s.mp3", list.get(counter[0]));
        try {
            AssetFileDescriptor fd = FileUtils.getAssetFileDescription(path);
            if (player.isPlaying()) return;
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
