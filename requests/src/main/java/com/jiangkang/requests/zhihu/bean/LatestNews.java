package com.jiangkang.requests.zhihu.bean;

import android.widget.ArrayAdapter;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jiangkang
 * @date 2017/10/21
 */

public class LatestNews implements Serializable{

//    private static final long serialVersionUID = 1L;

    @SerializedName("date")
    private String date;

    @SerializedName("stories")
    private ArrayList<Story> stories;


    public String getDate() {
        return date;
    }

    public LatestNews setDate(String date) {
        this.date = date;
        return this;
    }

    public ArrayList<Story> getStories() {
        return stories;
    }

    public LatestNews setStories(ArrayList<Story> stories) {
        this.stories = stories;
        return this;
    }

}
