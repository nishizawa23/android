
package com.android.contentproviderappnew;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ListActivity;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.ContentObserver;
import android.database.Cursor;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SimpleCursorAdapter;
//import android.app.LoaderManager;

public class MainActivity extends ListActivity {

    
    private final String TAG = "TestSimpleCourseAdapt";
    SimpleCursorAdapter mAdapter;
    private LoaderManager manager;
    @SuppressLint("NewApi")
   // @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
 //     //监听 uri 注册，自己实现PersonObserver
        getContentResolver().registerContentObserver(Uri.parse("content://com.ljq.provider.personproviderappnew/person"),
                true, new PersonObserver(new Handler()));
   
          mAdapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1, null,
                new String[] { "NAME" },
                new int[] { android.R.id.text1 },0);
          
          
          setListAdapter(mAdapter);
         manager = getLoaderManager();
         manager.initLoader(0, null, callbacks);
         
    }
    

    @SuppressLint("NewApi")
    private LoaderManager.LoaderCallbacks<Cursor> callbacks = new LoaderCallbacks<Cursor>() {
        
    @SuppressLint("NewApi")
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {
    // 在Loader创建的时候被调用，这里使用一个ContentProvider获取数据，所以使用CursorLoader返回数据
    Uri uri = Uri
      .parse("content://com.ljq.provider.personproviderappnew/person");
      CursorLoader loader = new CursorLoader(MainActivity.this, uri,
        null, null, null, null);
        Log.i(TAG, "onCreateLoader被执行。");
       return loader;
    } 
    
    @SuppressLint("NewApi")
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
                 //刷新SimpleCursorAdapter的数据
                 mAdapter.swapCursor(cursor);
                 // 重新设定适配器
   //              listview.setAdapter(mAdapter);
                 Log.i(TAG, "onLoadFinished被执行。");
    }
    @SuppressLint("NewApi")
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
                 // 当Loader被从LoaderManager中移除的时候，被执行，清空SimpleCursorAdapter适配器的Cursor
               mAdapter.swapCursor(null);
               Log.i(TAG, "onLoaderReset被执行。");
    }
};

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        menu.add(Menu.NONE, Menu.FIRST, 0, "add");
        menu.add(Menu.NONE, Menu.FIRST+1, 0, "del");
        return true;
    }
    
    @SuppressLint("NewApi")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        ContentResolver contentResolver = getContentResolver();
        
        ContentValues values;
        switch(item.getItemId())//得到被点击的item的itemId
        {
            
        case Menu.FIRST: //对应的ID就是在add方法中所设定的Id
            
            Uri insertUri = Uri.parse("content://com.ljq.provider.personproviderappnew/person");
            values = new ContentValues();
            //values.put("_id","1");
            values.put("name", "pete");
            values.put("phone", "13420934841");
            Uri uri = contentResolver.insert(insertUri, values);
            if (uri != null) {
                //result不为空证明删除成功，重新启动Loader，注意标识需要和之前init的标识一致。
                manager.restartLoader(0, null, callbacks);
            }
            break;
        case Menu.FIRST+1:
            
        //Uri deluri = Uri.parse("content://com.ljq.provider.personproviderappnew/person/1");
        Cursor cursor = contentResolver.query(Uri.parse("content://com.ljq.provider.personproviderappnew/person"), null, null, null, "id desc");
        Uri deluri = Uri.parse("content://com.ljq.provider.personproviderappnew/person/"+cursor.getCount());
        int uri1 = contentResolver.delete(deluri, null,null);
        if (uri1 == 1) {
            //result不为空证明删除成功，重新启动Loader，注意标识需要和之前init的标识一致。
            manager.restartLoader(0, null, callbacks);
        }
            break;
        }
        return true;
    }
    
    
    public class PersonObserver extends ContentObserver{
        
        public PersonObserver(Handler handler) {
        super(handler);
        }
        public void onChange(boolean selfChange) {
        Log.i(TAG,"personobserver onchange");
        }
    }
 
}
