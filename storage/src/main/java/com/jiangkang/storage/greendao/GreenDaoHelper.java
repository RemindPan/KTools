package com.jiangkang.storage.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.database.Database;

/**
 * Created by jiangkang on 2017/11/13.
 * description：
 */

public class GreenDaoHelper {

    private Context mAppContext;

    private DaoMaster.OpenHelper mOpenHelper;

    private SQLiteDatabase mDb;

    private DaoMaster mDaoMaster;

    private DaoSession mDaoSession;


    public GreenDaoHelper(Context context) {
        this.mAppContext = context.getApplicationContext();
        init();
    }

    private void init() {
        mOpenHelper = new DaoMaster.DevOpenHelper(mAppContext,"notes-db",null);
        mDb = mOpenHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(mDb);
        mDaoSession = mDaoMaster.newSession();
    }

    public NoteDao getNoteDao(){
        return mDaoSession.getNoteDao();
    }


}
