package com.jiangkang.ktools.mvvm;

import android.content.Context;
import android.view.View;

import com.jiangkang.hybrid.Khybrid;

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
        if (sourceUrl != null){
            new Khybrid().loadUrl(mContext,sourceUrl);
        }else {
            new Khybrid().loadUrl(mContext,"https://github.com/jiangkang/KTools");
        }
    }
}
