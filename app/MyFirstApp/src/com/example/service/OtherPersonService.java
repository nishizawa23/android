package com.example.service;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import android.R.string;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.domain.Person;

public class OtherPersonService {

	private DataBaseOpenHelper dbOpenHelper;

	// private Context context;

	public OtherPersonService(Context context) {
		// this.context = context;
		dbOpenHelper = new DataBaseOpenHelper(context);
	}

	public void save(Person person) {
		SQLiteDatabase database = dbOpenHelper.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put("name", person.getName());
		contentValues.put("age", person.getAge());
		database.insert("person","name", contentValues);
	}

	public void update(Person person) {
		SQLiteDatabase database = dbOpenHelper.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put("name", person.getName());
		contentValues.put("age", person.getAge());
		database.update("person", null, "personid=?",
				new String[] { String.valueOf(person.getId()) });
	}

	public Person find(Integer id) {
		SQLiteDatabase database = dbOpenHelper.getReadableDatabase();
		Cursor cursor = database.query("person", new String[] { "personid",
				"name", "age" }, "personid=?",
				new String[] { String.valueOf(id) }, null, null, null);
		if (cursor.moveToNext()) {
			return new Person(cursor.getInt(0), cursor.getString(1),
					cursor.getShort(2));
		}
		return null;
	}

	public void delete(Integer... ids) {
		if (ids.length > 0) {
			StringBuffer sb = new StringBuffer();
			String[] strIds = new String[ids.length];
			// for (Integer id : ids) {
			// sb.append('?').append(',');
			// }
			for (int i = 0; i < strIds.length; i++) {
				sb.append('?').append(',');
				strIds[i] = String.valueOf(ids[i]);
			}
			sb.deleteCharAt(sb.length() - 1);
			SQLiteDatabase database = dbOpenHelper.getWritableDatabase();
			database.delete("person", "personid in(" + sb.toString() + ")",
					strIds);
		}
	}

	public List<Person> getScrollData(int startResult, int maxResult) {
		List<Person> persons = new ArrayList<Person>();
		SQLiteDatabase database = dbOpenHelper.getReadableDatabase();
		Cursor cursor = database.query("person", new String[] { "personid",
				"name", "age" }, null, null, null, null, "personid desc",
				startResult + "," + maxResult);
		while (cursor.moveToNext()) {
			persons.add(new Person(cursor.getInt(0), cursor.getString(1),
					cursor.getShort(2)));
		}
		return persons;
	}

	public long getCount() {
		SQLiteDatabase database = dbOpenHelper.getReadableDatabase();
		Cursor cursor = database.query("person", new String[] { "count(*)" },
				null, null, null, null, null);
		if (cursor.moveToNext()) {
			return cursor.getLong(0);
		}
		return 0;
	}

}
