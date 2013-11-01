package com.example.db;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.domain.Person;
import com.example.myfirstapp.R;
import com.example.service.PersonService;

import android.R.string;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.LiveFolders;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class DBActivity extends Activity {
	/** Called when the activity is first created. */
	private static final String TAG = "DBActivity";
/*实现方法一
	 @Override
	 public void onCreate(Bundle savedInstanceState) {
	 super.onCreate(savedInstanceState);
	 setContentView(R.layout.main);
	 PersonService personService=new PersonService(this);
	 ListView listView = (ListView) this.findViewById(R.id.listView);
	
	 List<HashMap<String, String>> data = new ArrayList<HashMap<String,
	 String>>();
	 // HashMap<String, String> title = new HashMap<String, String>();
	 // title.put("personid", "编号");
	 // title.put("name", "姓名");
	 // title.put("age", "年龄");
	 // data.add(title);
	
	 List<Person> persons= personService.getScrollData(0, 10);
	 for (Person person : persons) {
	 HashMap<String, String> p = new HashMap<String, String>();
	 p.put("personid", String.valueOf(person.getId()));
	 p.put("name", person.getName());
	 p.put("age",String.valueOf(person.getAge()));
	 data.add(p);
	 }
	
	 // 适配器有：
	 // ArrayAdapter<T>
	 // simpAdapter
	 // SimpleCursorAdapter
	 SimpleAdapter adapter = new SimpleAdapter(DBActivity.this, data,
	 R.layout.personitem,
	 new String[] { "personid", "name", "age" },
	 new int[] {R.id.personid, R.id.name, R.id.age });
	 listView.setAdapter(adapter);
	
	 listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
	 @Override
	 // parent即为你点击的listView
	 // view为listview的外面布局
	 public void onItemClick(AdapterView<?> parent, View view, int position,
	 long id) {
	 ListView listView= (ListView) parent;
	 HashMap<String, String> itemdata= (HashMap<String, String>)
	 listView.getItemAtPosition(position);
	 String personid=itemdata.get("personid");
	 String name=itemdata.get("name");
	 String age=itemdata.get("age");
	 Log.i(TAG,view.getClass().getName());
	 Log.i(TAG, "personid: "+personid+ "   name: "+name+"   age:   "+age);
	 Log.i(TAG," position==id:"+ (position==id));
	Toast.makeText(DBActivity.this, name, Toast.LENGTH_LONG).show();
	 }
	
	 });
	
	 }
	
*/
	
//	实现方法二（游标）
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.main);
		setContentView(R.layout.dbmain);
		PersonService personService = new PersonService(this);
		ListView listView = (ListView) this.findViewById(R.id.listView);

		List<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
		// HashMap<String, String> title = new HashMap<String, String>();
		// title.put("personid", "编号");
		// title.put("name", "姓名");
		// title.put("age", "年龄");
		// data.add(title);

		// 适配器有：
		// ArrayAdapter<T>
		// simpAdapter
		// SimpleCursorAdapter
		Cursor cursor = personService.getRawScrollData(0, 10);
		
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(DBActivity.this,
				R.layout.personitem, cursor, new String[] { "_id", "name",
						"age" },
				new int[] { R.id.personid, R.id.name, R.id.age });
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			// parent即为你点击的listView
			// view为listview的外面布局
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ListView listView = (ListView) parent;
				Cursor cursor = (Cursor) listView.getItemAtPosition(position);
				String personid = String.valueOf(cursor.getInt(0));
				String name = String.valueOf(cursor.getString(1));
				String age = String.valueOf(cursor.getShort(2));
				Log.i(TAG, view.getClass().getName());
				Log.i(TAG, "personid: " + personid + "   name: " + name
						+ "   age:   " + age);
				Log.i(TAG, " position==id:" + (position == id));
				Toast.makeText(DBActivity.this, name, Toast.LENGTH_LONG).show();
			}

		});

	}
}