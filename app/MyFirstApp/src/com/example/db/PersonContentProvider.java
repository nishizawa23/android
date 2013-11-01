package com.example.db;

import com.example.service.DataBaseOpenHelper;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;

public class PersonContentProvider extends ContentProvider {
	private DataBaseOpenHelper dbOpenHelper;
	private static final int ALLPERSON = 1;
	private static final int PERSON = 2;
	private static final UriMatcher sMatcher = new UriMatcher(
			UriMatcher.NO_MATCH);
	static {
		sMatcher.addURI("com.jbridge.provider.personprovider", "person",
				ALLPERSON);
		sMatcher.addURI("com.jbridge.provider.personprovider", "person/#",
				PERSON);
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		int count = 0;
		switch (sMatcher.match(uri)) {
		case ALLPERSON:// com.jbridge.provider.personprovider/person
			count=db.delete("person", selection,selectionArgs);
			break;
		case PERSON:// com.jbridge.provider.personprovider/person/10
			long personid=ContentUris.parseId(uri);
			//防止他输入时String selection, String[] selectionArgs参数为空，这样就会修改表的所有数据了
			String where=TextUtils.isEmpty(selection)?"personid=?":selection+" and personid=?";
			String[] params=new String[]{String.valueOf(personid)}; 
			if (!TextUtils.isEmpty(selection)&&selectionArgs!=null) {
				params=new String[selectionArgs.length+1];
				for (int i=0;i<selectionArgs.length;i++) {
					params[i]=selectionArgs[i];
				}
				params[selectionArgs.length]=String.valueOf(personid);
			}
			count = db.delete("person", where,params);
			break;
		default:
			throw new IllegalArgumentException("Unknow Uri:" + uri);

		}
		
		return count;
	}

	@Override
	public String getType(Uri uri) {
		switch (sMatcher.match(uri)) {
		case ALLPERSON:// //数据集的MIME类型字符串则应该以vnd.android.cursor.dir/开头
			return "vnd.android.cursor.dir/personprovider.person";
		case PERSON://单一数据的MIME类型字符串应该以vnd.android.cursor.item/开头
			return "vnd.android.cursor.item/personprovider.person";
		default:
			throw new IllegalArgumentException("Unknow Uri:" + uri);
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		long id = 0;
		switch (sMatcher.match(uri)) {
		case ALLPERSON:// com.jbridge.provider.personprovider/person
			id = db.insert("person", "name", values);// 返回值是记录的行号，主键为int实际上就是主键值，主键为text则为行号
			return ContentUris.withAppendedId(uri, id);
		case PERSON:// com.jbridge.provider.personprovider/person/10
			id = db.insert("person", "name", values);// 返回值是记录的行号，主键为int实际上就是主键值，主键为text则为行号
			String path = uri.toString();
			return Uri.parse(path.substring(0, path.lastIndexOf("/")) + id);
		default:
			throw new IllegalArgumentException("Unknow Uri:" + uri);
		}
	}

	@Override
	public boolean onCreate() {
		dbOpenHelper=new DataBaseOpenHelper(this.getContext());
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
			String sortOrder) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		switch (sMatcher.match(uri)) {
		case ALLPERSON:// com.jbridge.provider.personprovider/person
			return db.query("person",projection, selection, selectionArgs,null, null, sortOrder);
		case PERSON:// com.jbridge.provider.personprovider/person/10
			long personid=ContentUris.parseId(uri);
			//防止他输入时String selection, String[] selectionArgs参数为空，这样就会修改表的所有数据了
			String where=TextUtils.isEmpty(selection)?"personid=?":selection+" and personid=?";
			String[] params=new String[]{String.valueOf(personid)}; 
			if (!TextUtils.isEmpty(selection)&&selectionArgs!=null) {
				params=new String[selectionArgs.length+1];
				for (int i=0;i<selectionArgs.length;i++) {
					params[i]=selectionArgs[i];
				}
				params[selectionArgs.length]=String.valueOf(personid);
			}
			return db.query("person",projection, where, params,null, null, sortOrder);

		default:
			throw new IllegalArgumentException("Unknow Uri:" + uri);

		}
		
		
	}

	@Override
	//返回值为影响的记录数
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		int count = 0;
		switch (sMatcher.match(uri)) {
		case ALLPERSON:// com.jbridge.provider.personprovider/person
			count = db.update("person", values, selection,selectionArgs);
			break;
		case PERSON:// com.jbridge.provider.personprovider/person/10
			long personid=ContentUris.parseId(uri);
			//防止他输入时String selection, String[] selectionArgs参数为空，这样就会修改表的所有数据了
			String where=TextUtils.isEmpty(selection)?"personid=?":selection+" and personid=?";
			String[] params=new String[]{String.valueOf(personid)}; 
			if (!TextUtils.isEmpty(selection)&&selectionArgs!=null) {
				params=new String[selectionArgs.length+1];
				for (int i=0;i<selectionArgs.length;i++) {
					params[i]=selectionArgs[i];
				}
				params[selectionArgs.length]=String.valueOf(personid);
			}
			count = db.update("person", values, where,params);
			break;
		default:
			throw new IllegalArgumentException("Unknow Uri:" + uri);

		}
		
		return count;
	}

}