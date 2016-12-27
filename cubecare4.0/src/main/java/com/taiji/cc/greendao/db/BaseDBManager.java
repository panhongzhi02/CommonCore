package com.taiji.cc.greendao.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * 作者：panho on 2016-12-26 14:21
 * 邮箱: panhongzhi02@163.com
 * 功能描述：
 */

public abstract class BaseDBManager {

    protected final static String dbName = "cubecare_db";

    protected Context mContext;

    protected DaoMaster.DevOpenHelper mOpenHelper;

    protected BaseDBManager(Context context){
        this.mContext = context;
        mOpenHelper = new DaoMaster.DevOpenHelper(context,dbName,null);
    }

    /**
     * 获取可读数据库
     * @return db 可读数据库
     */
    protected SQLiteDatabase getReadableDatabase(){
        if(mOpenHelper==null){
            mOpenHelper = new DaoMaster.DevOpenHelper(mContext,dbName,null);
        }
        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        return db;
    }

    /**
     * 获取可写数据库
     * @return db 可写数据库
     */
    protected SQLiteDatabase getWritableDatabase(){
        if(mOpenHelper==null){
            mOpenHelper = new DaoMaster.DevOpenHelper(mContext,dbName,null);
        }
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        return db;
    }

    protected DaoSession getReadableSession(){
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        return daoMaster.newSession();
    }

    protected DaoSession getWritableSession(){
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        return daoMaster.newSession();
    }

}