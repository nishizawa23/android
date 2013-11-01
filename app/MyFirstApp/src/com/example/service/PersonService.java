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
		// database.close();可以不关闭数据库，他里面会缓存一个数据库对象，如果以后还要用就直接用这个缓存的数据库对象。但通过
		// context.openOrCreateDatabase(arg0, arg1, arg2)打开的数据库必须得关闭
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

	// 获取分页数据，提供给SimpleCursorAdapter使用。
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
