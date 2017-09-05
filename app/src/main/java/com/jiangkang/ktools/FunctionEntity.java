package com.jiangkang.ktools;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import java.io.Serializable;

/**
 * Created by jiangkang on 2017/9/5.
 * description：
 */

public class FunctionEntity implements Serializable{

  private String name;

  private Class<? extends AppCompatActivity> activity;

  @IdRes private int resId;

  public FunctionEntity(String name, Class<? extends AppCompatActivity> activity, int resId) {
    this.name = name;
    this.activity = activity;
    this.resId = resId;
  }

  public FunctionEntity() {
  }

  public String getName() {
    return name;
  }

  public FunctionEntity setName(String name) {
    this.name = name;
    return this;
  }

  public Class<? extends AppCompatActivity> getActivity() {
    return activity;
  }

  public FunctionEntity setActivity(Class<? extends AppCompatActivity> activity) {
    this.activity = activity;
    return this;
  }

  public int getResId() {
    return resId;
  }

  public FunctionEntity setResId(int resId) {
    this.resId = resId;
    return this;
  }
}
