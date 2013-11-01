package com.example.service;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.domain.Person;

public class PersonService {

	private DataBaseOpenHelper dbOpenHelper;

	// private Context context;

	public PersonService(Context context) {
		// this.context = context;
		dbOpenHelper = new DataBaseOpenHelper(context);
	}

	public void save(Person person) {
		SQLiteDatabase database = dbOpenHelper.getWritableDatabase();
		database.beginTransaction();
		database.execSQL("insert into person(name,age)values(?,?)",
				new Object[] { person.getName(), person.getAge() });
		// database.close();���Բ��ر����ݿ⣬������Ỻ��һ�����ݿ��������Ժ�Ҫ�þ�ֱ���������������ݿ���󡣵�ͨ��
		// context.openOrCreateDatabase(arg0, arg1, arg2)�򿪵����ݿ����ùر�
		database.setTransactionSuccessful();
		database.endTransaction();

	}

	public void update(Person person) {
		SQLiteDatabase database = dbOpenHelper.getWritableDatabase();
		database.execSQL(
				"update person set name=?,age=? where personid=?",
				new Object[] { person.getName(), person.getAge(),
						person.getId() });
	}

	public Person find(Integer id) {
		SQLiteDatabase database = dbOpenHelper.getReadableDatabase();
		Cursor cursor = database.rawQuery(
				"select * from person where personid=?",
				new String[] { String.valueOf(id) });
		if (cursor.moveToNext()) {
			return new Person(cursor.getInt(0), cursor.getString(1),
					cursor.getShort(2));
		}
		return null;
	}

	public void delete(Integer... ids) {
		if (ids.length > 0) {
			StringBuffer sb = new StringBuffer();
			for (Integer id : ids) {
				sb.append('?').append(',');
			}
			sb.deleteCharAt(sb.length() - 1);
			SQLiteDatabase database = dbOpenHelper.getWritableDatabase();
			database.execSQL(
					"delete from person where personid in(" + sb.toString()
							+ ")", ids);
		}
	}

	public List<Person> getScrollData(int startResult, int maxResult) {
		List<Person> persons = new ArrayList<Person>();
		SQLiteDatabase database = dbOpenHelper.getReadableDatabase();
		Cursor cursor = database.rawQuery(
				"select * from person limit ?,?",
				new String[] { String.valueOf(startResult),
						String.valueOf(maxResult) });
		while (cursor.moveToNext()) {
			persons.add(new Person(cursor.getInt(0), cursor.getString(1),
					cursor.getShort(2)));
		}
		return persons;
	}

	// ��ȡ��ҳ���ݣ��ṩ��SimpleCursorAdapterʹ�á�
	public Cursor getRawScrollData(int startResult, int maxResult) {
		List<Person> persons = new ArrayList<Person>();
		SQLiteDatabase database = dbOpenHelper.getReadableDatabase();
		return database.rawQuery(
				"select personid as _id ,name,age from person limit ?,?",
				new String[] { String.valueOf(startResult),
						String.valueOf(maxResult) });

	}

	public long getCount() {
		SQLiteDatabase database = dbOpenHelper.getReadableDatabase();
		Cursor cursor = database.rawQuery("select count(*) from person", null);
		if (cursor.moveToNext()) {
			return cursor.getLong(0);
		}
		return 0;
	}

}
