package com.jiangkang.ktools;

import android.app.Application;

import com.jiangkang.tools.King;

/**
 * Created by jiangkang on 2017/9/6.
 */

public class KApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        King.init(this);
    }


}
