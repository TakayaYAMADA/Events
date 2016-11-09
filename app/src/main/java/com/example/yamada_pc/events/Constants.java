package com.example.yamada_pc.events;

/**
 * Created by yamada-PC on 2016/11/09.
 */

import android.provider.BaseColumns;

public interface Constants extends BaseColumns {
    public static final String TABLE_NAME = "events";

    //Eventsデータベースの列
    public static final String TIME = "time";
    public static final String TITLE = "title";
}
