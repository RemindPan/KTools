package com.jiangkang.storage.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by jiangkang on 2017/11/13.
 * description：
 */

@Entity
public class Note {

    @Id(autoincrement = true)
    private Long id;


    @Property(nameInDb = "content")
    private String content;


    @Property(nameInDb = "timeAdded")
    private String timeAdded;


    @Generated(hash = 1224059569)
    public Note(Long id, String content, String timeAdded) {
        this.id = id;
        this.content = content;
        this.timeAdded = timeAdded;
    }

    @Generated(hash = 1272611929)
    public Note() {
    }


    public String getContent() {
        return content;
    }

    public Note setContent(String content) {
        this.content = content;
        return this;
    }

    public String getTimeAdded() {
        return timeAdded;
    }

    public Note setTimeAdded(String timeAdded) {
        this.timeAdded = timeAdded;
        return this;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
