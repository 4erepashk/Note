package com.example.p19;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "tab";
    public static final String TABLE_CONTACTS = "lis";

    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_CONTENT = "content";
    public static final String KEY_TIME = "time";
    public static final String KEY_VALUE = "value";

    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_CONTACTS + "(" + KEY_ID  + " integer primary key," + KEY_NAME + " text," + KEY_CONTENT + " text,"  +KEY_TIME + " text, "+ KEY_VALUE + " text" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_CONTACTS);

        onCreate(sqLiteDatabase);
    }
}
