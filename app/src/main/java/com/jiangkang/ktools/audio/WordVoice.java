package com.jiangkang.ktools.audio;

/**
 * Created by jiangkang on 2017/9/28.
 * description：
 */

public enum WordVoice {

  Zero("0","tts_0"),
  One("1","tts_1"),
  Two("2","tts_2"),
  Three("3","tts_3"),
  Four("4","tts_4"),
  Five("5","tts_5"),
  Six("6","tts_6"),
  Seven("7","tts_7"),
  Eight("8","tts_8"),
  Nine("9","tts_9"),
  Dot(".","tts_dot"),
  Yuan("元","tts_yuan");


  private String text;

  private String filename;

  WordVoice(String text, String filename) {
    this.text = text;
    this.filename = filename;
  }

  public String getText() {
    return text;
  }

  public WordVoice setText(String text) {
    this.text = text;
    return this;
  }

  public String getFilename() {
    return filename;
  }

  public WordVoice setFilename(String filename) {
    this.filename = filename;
    return this;
  }
}
