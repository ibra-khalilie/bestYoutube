package com.example.mybestyoutube.Dao;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Dao {

    protected SQLiteDatabase db = null;
    protected SQLiteOpenHelper baseHelper = null;


    public Dao(SQLiteOpenHelper baseHelper) {
        this.baseHelper = baseHelper;
    }

    public SQLiteDatabase open(){

        db = baseHelper.getWritableDatabase();
        return db;
    }

    public void close(){
        db.close();
    }

    public SQLiteDatabase getDb(){
        return db;
    }
}
