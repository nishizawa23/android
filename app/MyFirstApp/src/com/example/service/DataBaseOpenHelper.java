package com.example.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseOpenHelper extends SQLiteOpenHelper {
	// ��û��ʵ����,�ǲ����������๹�����Ĳ���,��������Ϊ��̬
	private static String dbname = "zyj";
	private static int version = 1;

	public DataBaseOpenHelper(Context context) {
		// ��һ��������Ӧ�õ�������
		// �ڶ���������Ӧ�õ����ݿ�����
		// ����������CursorFactoryָ����ִ�в�ѯʱ���һ���α�ʵ���Ĺ�����,����Ϊnull,����ʹ��ϵͳĬ�ϵĹ�����
		// ���ĸ����������ݿ�汾�������Ǵ���0��int
		super(context, dbname, null, version);
		// TODO Auto-generated constructor stub
	}

	public DataBaseOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE IF NOT EXISTS person (personid integer primary key autoincrement, name varchar(20), age INTEGER)");
	}

	// onUpgrade()���������ݿ�汾ÿ�η����仯ʱ������û��ֻ��ϵ����ݿ��ɾ����Ȼ�������´�����
	// һ����ʵ����Ŀ���ǲ����������ģ���ȷ���������ڸ������ݿ��ṹʱ����Ҫ�����û���������ݿ��е����ݲ��ᶪʧ,�Ӱ汾�����µ��汾����
	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		db.execSQL("DROP TABLE IF EXISTS person");
		onCreate(db);
	}

}