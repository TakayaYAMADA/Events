package com.example.yamada_pc.events;

import android.support.v7.app.AppCompatActivity;

import static android.provider.BaseColumns._ID;
import static com.example.yamada_pc.events.Constants.TABLE_NAME;
import static com.example.yamada_pc.events.Constants.TIME;
import static com.example.yamada_pc.events.Constants.TITLE;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.StringBuilderPrinter;
import android.widget.TextView;

public class Events extends Activity {

    private EventsData events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        events = new EventsData(this);
        try{
            addEvent("Hello Android");
            Cursor cursor = getEvents();
            showEvents(cursor);
        }finally{
            events.close();
        }
    }

    private void addEvent(String string){
        // Eventデータソースに新しい行を挿入する
        // 削除、更新も同様の方法で実行できる
        SQLiteDatabase db = events.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TIME, System.currentTimeMillis());
        values.put(TITLE, string);
        db.insertOrThrow(TABLE_NAME, null, values);
    }

    private static String[] FROM = {_ID, TIME, TITLE};
    private static String ORDER_BY = TIME + " DESC ";
    private Cursor getEvents(){
        // 管理されたクエリを実行する。Activityは
        // クローズのほか、必要な場合は再クエリを処理する
        SQLiteDatabase db = events.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, FROM, null,null,null,null, ORDER_BY);
        startManagingCursor(cursor);
        return cursor;
    }

    private void showEvents(Cursor cursor){
        // カーソルの全ての内容を大きな文字列に詰め込む
        StringBuilder builder = new StringBuilder("saved events:\n");
        while (cursor.moveToNext()){
            // インデックスの取得にはgetColumnsOrThrow()も使える
            long id = cursor.getLong(0);
            long time = cursor.getLong(1);
            String title = cursor.getString(2);
            builder.append(id).append(": ");
            builder.append(time).append(": ");
            builder.append(title).append("\n");
        }
        TextView text = (TextView) findViewById(R.id.text);
        text.setText(builder);
    }
}
