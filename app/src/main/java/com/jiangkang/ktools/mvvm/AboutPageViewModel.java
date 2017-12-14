package com.jiangkang.ktools.mvvm;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.jiangkang.ktools.web.WebActivity;

/**
 * Created by jiangkang on 2017/11/29.
 * description：
 */

public class AboutPageViewModel {

    private String author;

    private String sourceUrl;

    private Context mContext;

    public AboutPageViewModel(Context context) {
        this.mContext = context;
    }

    public String getAuthor() {
        return author;
    }

    public AboutPageViewModel setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public AboutPageViewModel setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
        return this;
    }

    private boolean findSubString(String dst, String sub){
        if (dst.contains(sub)){
            return true;
        }
        return false;
    }





    public void onClick(View view) {
        Bundle bundle = new Bundle();
        if (sourceUrl != null){
            bundle.putString("launchUrl", sourceUrl);
        }else {
            bundle.putString("launchUrl", "https://github.com/jiangkang/KTools");
        }
        WebActivity.launch(mContext, bundle);
    }
}
