package com.jiangkang.requests.zhihu.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 *
 * @author jiangkang
 * @date 2017/10/21
 */

public class StartPageInfo implements Serializable{


    @SerializedName("text")
    private String text;

    @SerializedName("img")
    private String imgUrl;


    public String getText() {
        return text;
    }

    public StartPageInfo setText(String text) {
        this.text = text;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public StartPageInfo setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }
}
