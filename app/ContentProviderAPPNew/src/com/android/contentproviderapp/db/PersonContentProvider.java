package com.android.contentproviderapp.db;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;


/**
 * 内容提供者
 * 
 * 作用：统一数据访问方式，方便外部调用
 * 
 * @author pete
 * 
 */
public class PersonContentProvider extends ContentProvider {
    // 数据集的MIME类型字符串则应该以vnd.android.cursor.dir/开头
    public static final String PERSONS_TYPE = "vnd.android.cursor.dir/person";
    // 单一数据的MIME类型字符串应该以vnd.android.cursor.item/开头
    public static final String PERSONS_ITEM_TYPE = "vnd.android.cursor.item/person";
    public static final String AUTHORITY = "com.ljq.provider.personproviderappnew";// 主机名
    /* 自定义匹配码 */
    public static final int PERSONS = 1;
    /* 自定义匹配码 */
    public static final int PERSON = 2;
    public static final Uri PERSONS_URI = Uri.parse("content://" + AUTHORITY + "/person");
    private DBOpenHelper dbOpenHelper = null;

    // UriMatcher类用来匹配Uri，使用match()方法匹配路径时返回匹配码
    private static final UriMatcher uriMatcher;
    static {
        // 常量UriMatcher.NO_MATCH表示不匹配任何路径的返回码
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        // 如果match()方法匹配content://com.ljq.provider.personprovider/person路径，返回匹配码为PERSONS
        uriMatcher.addURI(AUTHORITY, "person", PERSONS);
        // 如果match()方法匹配content://com.ljq.provider.personprovider/person/230路径，返回匹配码为PERSON
        uriMatcher.addURI(AUTHORITY, "person/#", PERSON);
    }
    
    @Override
    public boolean onCreate() {
        dbOpenHelper = new DBOpenHelper(this.getContext());
        return true;
    }
    
    @Override
    public Uri insert(Uri uri, ContentValues values){
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        long id = 0;
        switch (uriMatcher.match(uri)) {
        case PERSONS:
            id = db.insert("person", "name", values);// 返回的是记录的行号，主键为int，实际上就是主键值
            //通知监听此uri的实例
            getContext().getContentResolver().notifyChange(uri, null);
            return ContentUris.withAppendedId(uri, id);
        case PERSON:
            id = db.insert("person", "name", values);
            String path = uri.toString();
            return Uri.parse(path.substring(0, path.lastIndexOf("/"))+id); // 替换掉id
        default:
            throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }
    
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        int count = 0;
        switch (uriMatcher.match(uri)) {
        case PERSONS:
            count = db.delete("person", selection, selectionArgs);
            break;
        case PERSON:
            // 下面的方法用于从URI中解析出id，对这样的路径content://com.ljq.provider.personprovider/person/10
            // 进行解析，返回值为10
            long personid = ContentUris.parseId(uri);
            String where = "id=" + personid;// 删除指定id的记录
            where += !TextUtils.isEmpty(selection) ? " and (" + selection + ")" : "";// 把其它条件附加上
            count = db.delete("person", where, selectionArgs);
            break;
        default:
            throw new IllegalArgumentException("Unknown URI " + uri);
        }
        db.close();
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
            String[] selectionArgs) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        int count = 0;
        switch (uriMatcher.match(uri)) {
        case PERSONS:
            count = db.update("person", values, selection, selectionArgs);
            break;
        case PERSON:
            // 下面的方法用于从URI中解析出id，对这样的路径content://com.ljq.provider.personprovider/person/10
            // 进行解析，返回值为10
            long personid = ContentUris.parseId(uri);
            String where = "id=" + personid;// 获取指定id的记录
            where += !TextUtils.isEmpty(selection) ? " and (" + selection + ")" : "";// 把其它条件附加上
            count = db.update("person", values, where, selectionArgs);
            break;
        default:
            throw new IllegalArgumentException("Unknown URI " + uri);
        }
        db.close();
        return count;
    }
    
    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
        case PERSONS:
            return PERSONS_TYPE;
        case PERSON:
            return PERSONS_ITEM_TYPE;
        default:
            throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
            String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
      
        switch (uriMatcher.match(uri)) {
        case PERSONS:
            //return db.query("person", projection, selection, selectionArgs, null, null, sortOrder);
            //简单代替db.query(),使用 SimpleCursorAdapter 主键用_id,如果不能修改数据库，那就用主键 as _id来申明别名
            return  db.rawQuery("select id as _id,name,phone from person order by id asc",null);
    
        case PERSON:
            // 下面的方法用于从URI中解析出id，对这样的路径content://com.ljq.provider.personprovider/person/10
            // 进行解析，返回值为10
            long personid = ContentUris.parseId(uri);
            String where = "id=" + personid;// 获取指定id的记录
            where += !TextUtils.isEmpty(selection) ? " and (" + selection + ")" : "";// 把其它条件附加上
            return db.query("person", projection, where, selectionArgs, null, null, sortOrder);
        default:
            throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }
}