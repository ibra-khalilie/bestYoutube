package com.example.mybestyoutube.Db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class YoutubeDbHelper extends SQLiteOpenHelper {


    private static final int VERSION = 1;
    private static  final String DATABASE_NAME = "todo.db";

    public static final String KEY = "id";
    public static final String TITRE = "titre";
    public static final String DESCRIPTION = "description";
    public static final String URL = "url";
    public static final String CATEGORIE = "categorie";
    public static final String TABLE_NAME = "Todo";

    public static final int KEY_COLUMN_INDEX = 0;
    public static final int TITRE_COLUMN_INDEX = 1;
    public static final int DESCRIPTION_COLUMN_INDEX = 2;
    public static final int URL_COLUMN_INDEX = 3;
    public static final int CATEGORIE_COLUMN_INDEX = 4;

    private static final String TABLE_CREATE = " CREATE TABLE " +
            TABLE_NAME +
            " ( " + KEY + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            TITRE + " TEXT, " +
            DESCRIPTION + " TEXT, " +
            URL + " TEXT, " +
            CATEGORIE + " TEXT" +
            " ); ";

    private static final String TABLE_DROP = " DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public YoutubeDbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(TABLE_DROP);
        onCreate(db);
    }


}
