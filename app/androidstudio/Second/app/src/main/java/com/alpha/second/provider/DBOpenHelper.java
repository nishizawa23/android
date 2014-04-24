package com.alpha.second.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pete_doherty on 14-4-19.
 */
public class DBOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE = "second.db";
    private static final int VERSION = 4;

    // 第三个参数CursorFactory指定在执行查询时获得一个游标实例的工厂类,
    // 设置为null,代表使用系统默认的工厂类
    public DBOpenHelper(Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //如果允许，主键（PRIMARY KEY)设置为_id才能满足SimpleCursorAdapter函数
        //否则回出现 column '_id' does not exist
        sqLiteDatabase.execSQL("CREATE TABLE PERSON (ID INTEGER PRIMARY KEY AUTOINCREMENT,_id VARCHAR(20),NAME VARCHAR(20), PHONE VARCHAR(20))");
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS PERSON");
        onCreate(sqLiteDatabase);

    }
}
