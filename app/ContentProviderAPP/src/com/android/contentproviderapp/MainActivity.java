
package com.android.contentproviderapp;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.ContentObserver;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends  ListActivity{
    
    private final String TAG = "TestSimpleCourseAdapt";
    SimpleCursorAdapter mAdapter;
    @SuppressLint("NewApi")
    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
 //     //监听 uri 注册，自己实现PersonObserver
        getContentResolver().registerContentObserver(Uri.parse("content://com.ljq.provider.personproviderapp/person"),
                true, new PersonObserver(new Handler()));
        
        Log.i(TAG,"start insert value");
        ContentResolver contentResolver = getContentResolver();
        Uri insertUri = Uri.parse("content://com.ljq.provider.personproviderapp/person");
        ContentValues values = new ContentValues();
        //values.put("_id","1");
        values.put("name", "pete");
        values.put("phone", "13420934841");
        Uri uri = contentResolver.insert(insertUri, values);
        Log.i(TAG, uri.toString());
        //values.put("_id","2");
        values.put("name", "ljq");
        values.put("phone", "1350000009");
        uri = contentResolver.insert(insertUri, values);
        Log.i(TAG, uri.toString());
        //values.put("_id","3");
        values.put("name", "lians");
        values.put("phone", "1350000008");
        uri = contentResolver.insert(insertUri, values);
        Log.i(TAG, uri.toString());
        
        uri = Uri.parse("content://com.ljq.provider.personproviderapp/person");
        Cursor cursor = contentResolver.query(uri, null, null, null, "id desc");
        
        while(cursor.moveToNext()){
            int personid = cursor.getInt(cursor.getColumnIndex("_id"));
            String name = cursor.getString(cursor.getColumnIndex("NAME"));
            String phone = cursor.getString(cursor.getColumnIndex("PHONE"));
            Log.i(TAG, "personid="+ personid + ",name="+ name+ ",phone="+ phone);
        }
        if(cursor != null){       
            startManagingCursor(cursor);
         }       
          mAdapter = new SimpleCursorAdapter(this,
                  android.R.layout.simple_list_item_1, cursor,
                //new String[] { "_id","NAME" },
                //new int[] { android.R.id.text1,android.R.id.text1 },0);
                new String[] { "NAME" },
                new int[] { android.R.id.text1 },0);
          
          //listView.setAdapter(adapter);
          setListAdapter(mAdapter);
          stopManagingCursor(cursor);
    }
    
    public class PersonObserver extends ContentObserver{
        public PersonObserver(Handler handler) {
        super(handler);
        }
        public void onChange(boolean selfChange) {
        Log.i(TAG,"personobserver onchange");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
