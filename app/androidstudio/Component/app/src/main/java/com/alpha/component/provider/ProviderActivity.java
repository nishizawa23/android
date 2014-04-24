package com.alpha.component.provider;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Loader;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.alpha.component.app.MainActivity;
import com.alpha.component.app.R;


import java.util.Observable;
import java.util.logging.Handler;

public class ProviderActivity extends ActionBarActivity {

    public static final String AUTHORITY = "com.alpha.second.provider";
    public static final Uri PERSONS_URI = Uri.parse("content://"+AUTHORITY + "/person");
    public static final Uri PERSONS_URIS = Uri.parse("content://"+AUTHORITY + "/person/#");
    private final static String TAG = "ProviderActivity";
    private PersonObserver Observer = null;
    private ListView listview;
    private SimpleCursorAdapter mAdapter;
/*
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new MyCursorLoader(getApplicationContext());//3.去数据库读取数据等要消耗大量时间的操作放在
        //自定义 CursorLoader 的 onLoadInBackground
    }
*/
    private LoaderManager.LoaderCallbacks<Cursor> callbacks = new LoaderManager.LoaderCallbacks<Cursor>() {

        @Override
        public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
            // 在Loader创建的时候被调用，这里使用一个ContentProvider获取数据，所以使用CursorLoader返回数据
            CursorLoader loader = new CursorLoader(ProviderActivity.this, PERSONS_URI,null, null, null, null);
            Log.i(TAG, "onCreateLoader被执行。");
            return loader;
        }

        @Override
        public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {

            mAdapter.swapCursor(cursor);
            // 重新设定适配器
            listview.setAdapter(mAdapter);

        }

        @Override
        public void onLoaderReset(Loader<Cursor> cursorLoader) {

        }
    };
    private String delete_name;
    private LoaderManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);

        listview = (ListView) findViewById(R.id.listView);

        ContentResolver contentResolver = getContentResolver();

        Observer = new PersonObserver();
        contentResolver.registerContentObserver(PERSONS_URI,true,Observer);

        Uri insertUri = PERSONS_URI;
        ContentValues values = new ContentValues();
        values.put("name", "ljq");
        values.put("phone", "1350000009");
        Uri uri = contentResolver.insert(insertUri, values);
        Log.i(TAG, uri.toString());

        manager = getLoaderManager();
        manager.initLoader(0, null, callbacks);

        //Cursor cursor = contentResolver.query(uri, null, null, null, "id asc");
        /*Cursor cursor = contentResolver.query(PERSONS_URI, null, null, null, "id desc");
        //LoaderManager(cursor);
        while(cursor.moveToNext()){
            int person_id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));
            Log.i(TAG, "person_id="+ person_id + ",name="+ name+ ",phone="+ phone);
        }*/
/*
        ListAdapter adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_expandable_list_item_1, null,
                new String[] { "name" }, new int[] { android.R.id.text1 });
*/
        mAdapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2, null,
                new String[] { "name","phone" }, new int[] { android.R.id.text1,android.R.id.text2 },0);

        listview.setAdapter(mAdapter);

        registerForContextMenu(listview);

        //cursor.close();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        // 声明一个上下文菜单，contentmenu中声明了两个菜单，添加和删除
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.provider_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.action_add:
                AlertDialog.Builder builder = new AlertDialog.Builder(
                 this);
                // 加载一个自定义布局，add_name中有一个EditText和Button控件。
               //final View view = LayoutInflater.from(this).inflate(
               //                    R.layout.add_name, null);

            case R.id.action_del:
                // 获取菜单选项的信息
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
                                     .getMenuInfo();
                             // 获取到选项的TextView控件，并得到选中项的那么
                //TextView tv = (TextView) info.targetView;
                //Log.v(TAG,"info.position is :"+info.position);
                //Log.v(TAG,"item toString :"+mAdapter.getItem(info.position));
                //mAdapter.getItem(info.position).toString();

                /*
                String name = tv.getText().toString();



                ContentResolver contentResolver = getContentResolver();
                String where = "name=?";
                String[] selectionArgs = { name };
                int count = contentResolver.delete(PERSONS_URI, where, selectionArgs);
                if (count == 1) {
                                             //这个操作仅删除单挑记录，如果删除行为1 ，则重新启动Loader
                                             manager.restartLoader(0, null, callbacks);
                            }
                            Log.i(TAG, "删除数据成功，name="+name);
                            */
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.provider_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class PersonObserver extends ContentObserver {

        PersonObserver(){
            super(new android.os.Handler());
        }

        @Override
        public void onChange(boolean selfChange) {
            //此处可以进行相应的业务处理
            Log.v(TAG,"changed selfChange is "+selfChange);
        }
    }

}
