package com.android.contentproviderapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {
    // 类没有实例化,是不能用作父类构造器的参数,必须声明为静态
    private static final String DBNAME = "ljq.db";
    private static final int VERSION = 4;
    
    // 第三个参数CursorFactory指定在执行查询时获得一个游标实例的工厂类,
    // 设置为null,代表使用系统默认的工厂类
    public DBOpenHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    //如果允许，主键（PRIMARY KEY)设置为_id才能满足SimpleCursorAdapter函数
    //否则回出现 column '_id' does not exist
    public void onCreate(SQLiteDatabase db) {
      //  db.execSQL("CREATE TABLE PERSON (ID INTEGER PRIMARY KEY AUTOINCREMENT, _id VARCHAR(20),NAME VARCHAR(20), PHONE VARCHAR(20))");
      db.execSQL("CREATE TABLE PERSON (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME VARCHAR(20), PHONE VARCHAR(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 注：生产环境上不能做删除操作
        db.execSQL("DROP TABLE IF EXISTS PERSON"); 
        onCreate(db);
    }
}