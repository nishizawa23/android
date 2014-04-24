package com.alpha.component.activitytest;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.alpha.component.app.R;

public class ActivityTest extends ActionBarActivity {

    private static final String TAG = "ActivityTest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.v(TAG,"onRestoreInstanceState");
    }

    @Override
    protected void onStart() {
        Log.v(TAG,"onStart" );
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.v(TAG,"onResume");
        super.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.v(TAG,"onSaveInstanceState");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onPause() {
        Log.v(TAG,"onPause");
        super.onPause();
    }

    @Override
    protected void onRestart() {
        Log.v(TAG,"onRestart");
        super.onRestart();
    }

    @Override
    protected void onStop() {
        Log.v(TAG,"onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.v(TAG,"onDestroy");
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_test, menu);
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

}
