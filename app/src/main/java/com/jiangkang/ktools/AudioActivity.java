package com.jiangkang.ktools;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaDataSource;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
import android.support.annotation.RawRes;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.style.TtsSpan;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.jiangkang.ktools.audio.WordVoice;
import com.jiangkang.tools.utils.FileUtils;
import com.jiangkang.tools.utils.ToastUtils;

import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AudioActivity extends AppCompatActivity {

  private static final String TAG = "AudioActivity";

  @BindView(R.id.et_text_content) EditText etTextContent;

  @BindView(R.id.btn_text_to_speech) Button btnTextToSpeech;

  @BindView(R.id.btn_play_single_sound) Button btnPlaySingleSound;

  @BindView(R.id.btn_play_multi_sounds) Button btnPlayMultiSounds;

  private TextToSpeech.OnInitListener onInitListener;

  private TextToSpeech speech;

  private int counter = 0;

  private MediaPlayer.OnCompletionListener onCompletionListener;

  private MediaPlayer player = new MediaPlayer();

  public static void launch(Context context, Bundle bundle) {
    Intent intent = new Intent(context, AudioActivity.class);
    if (bundle != null) {
      intent.putExtras(bundle);
    }
    context.startActivity(intent);
  }

  @Override protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_audio);
    ButterKnife.bind(this);
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    if (speech != null) {
      speech.shutdown();
    }
  }

  @OnClick(R.id.btn_text_to_speech) public void onBtnTextToSpeechClicked() {
    onInitListener = new TextToSpeech.OnInitListener() {
      @Override public void onInit(int status) {
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

  @OnClick(R.id.btn_play_single_sound) public void onBtnPlaySingleSoundClicked() {
    MediaPlayer player = MediaPlayer.create(this, R.raw.tts_success);
    player.start();
  }

  @OnClick(R.id.btn_play_multi_sounds) public void onBtnPlayMultiSoundsClicked() {

    final List<String> list = new ArrayList<>();
    list.add("1");
    list.add("2");
    list.add("3");
    list.add("4");
    list.add("5");
    list.add("6");
    list.add("7");
    list.add("8");
    list.add("9");
    list.add("dot");
    list.add("0");
    list.add("yuan");


    speakChineseNum(list);

  }

  private void speakChineseNum(final List list) {
    String path = String.format("sound/tts_%s.mp3",list.get(counter));
    try {
      AssetFileDescriptor fd = FileUtils.getAssetFileDescription(path);
      player.setDataSource(fd.getFileDescriptor(), fd.getStartOffset(),
          fd.getLength());
      player.prepare();
      player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
        @Override public void onPrepared(MediaPlayer mp) {
          mp.start();
        }
      });
      player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
        @Override public void onCompletion(MediaPlayer mp) {
          mp.reset();
          counter++;
          if (counter < list.size()){
            try {
              AssetFileDescriptor fileDescriptor = FileUtils.getAssetFileDescription(String.format("sound/tts_%s.mp3",list.get(counter)));
              mp.setDataSource(fileDescriptor.getFileDescriptor(),fileDescriptor.getStartOffset(),fileDescriptor.getLength());
              mp.prepare();
            } catch (IOException e) {
              e.printStackTrace();
            }
          }else {
            mp.release();
          }
        }
      });
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
