package com.example.yamada_pc.events;

import static android.provider.BaseColumns._ID;
import static com.example.yamada_pc.events.Constants.TABLE_NAME;
import static com.example.yamada_pc.events.Constants.TIME;
import static com.example.yamada_pc.events.Constants.TITLE;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Time;

/**
 * Created by yamada-PC on 2016/11/09.
 */

public class EventsData extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "events.db";
    private static final int DATABASE_VERSION = 1;

    /** Eventsデータベースのためのヘルパオブジェクトを作る*/
    public EventsData(Context ctx){
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" + _ID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TIME
                    + " INTEGER, " + TITLE + " TEXT NOT NULL);" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
