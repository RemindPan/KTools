package com.jiangkang.tools.bean;

/**
 * Created by jiangkang on 2017/10/31.
 * description：歌曲实体类
 */

public final class Song {


    private String name;

    private String artist;

    private String coverUri;

    //单位 second
    private long time;

    //文件位置
    private String location;


    public String getName() {
        return name;
    }

    public Song setName(String name) {
        this.name = name;
        return this;
    }

    public String getArtist() {
        return artist;
    }

    public Song setArtist(String artist) {
        this.artist = artist;
        return this;
    }

    public String getCoverUri() {
        return coverUri;
    }

    public Song setCoverUri(String coverUri) {
        this.coverUri = coverUri;
        return this;
    }

    public long getTime() {
        return time;
    }

    public Song setTime(long time) {
        this.time = time;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public Song setLocation(String location) {
        this.location = location;
        return this;
    }


    @Override
    public String toString() {
        return String.format("name = %s, artist = %s, location = %s",
                name,
                artist,
                location);
    }
}
