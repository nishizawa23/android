package com.alpha.component.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.alpha.component.app.R;

public class ServiceActivity extends ActionBarActivity implements View.OnClickListener{

    private static final String TAG = "ServiceActivity";
    private static final String SERVICE_TEST_ACTION_FOO = "com.alpha.component.service.action.FOO";
    private static final String SERVICE_TEST_ACTION_BAZ = "com.alpha.component.service.action.BAZ";
    private static final String EXTRA_PARAM1 = "com.alpha.component.service.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.alpha.component.service.extra.PARAM2";
    Context mContext;
    BindServiceTest.SimpleBinder mSimpleBinder;
    Button button_start;
    Button button_stop;
    Button button_onBind;
    Button button_unBind;
    Button button_intent_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        mContext = getApplicationContext();
        button_start = (Button) findViewById(R.id.start_server_button);
        button_stop = (Button) findViewById(R.id.stop_server_button);
        button_onBind = (Button) findViewById(R.id.on_bind);
        button_unBind = (Button) findViewById(R.id.un_bind);
        button_intent_start = (Button) findViewById(R.id.button_intent_server_start);



        View.OnClickListener BStartListener = new Button.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(mContext, ServiceTest.class);
                mIntent.setAction("send from ActivityTest startService");
                mContext.startService(mIntent);

            }
        };

        View.OnClickListener BStopListener = new Button.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(mContext, ServiceTest.class);
                mIntent.setAction("send from Activity stopService");
                mContext.stopService(mIntent);
            }
        };

        View.OnClickListener BonBindListener = new Button.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent mBindIntent = new Intent(mContext, BindServiceTest.class);
                mBindIntent.setAction("send from ActivityTest onBind");
                bindService(mBindIntent, conn, Service.BIND_AUTO_CREATE);

            }
        };

        View.OnClickListener BonUnBindListener = new Button.OnClickListener(){

            @Override
            public void onClick(View view) {
                unbindService(conn);
            }
        };

        //button_start.setOnClickListener(BStartListener);
        //button_stop.setOnClickListener(BStopListener);
        //button_onBind.setOnClickListener(BonBindListener);
        //button_unBind.setOnClickListener(BonUnBindListener);
        button_start.setOnClickListener(this);
        button_stop.setOnClickListener(this);
        button_onBind.setOnClickListener(this);
        button_unBind.setOnClickListener(this);
        button_intent_start.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent mIntent = new Intent(mContext, ServiceTest.class);

        switch (view.getId()){
            case R.id.start_server_button:
                mIntent.setAction(SERVICE_TEST_ACTION_FOO);
                mIntent.putExtra(EXTRA_PARAM1,false);
                mIntent.putExtra(EXTRA_PARAM2,"hello world");
                mContext.startService(mIntent);
                break;
            case R.id.stop_server_button:
                mIntent.setAction("send from Activity stopService");
                mContext.stopService(mIntent);
                break;
            case R.id.on_bind:
                Intent mBindIntent = new Intent(mContext, BindServiceTest.class);
                mBindIntent.setAction("send from ActivityTest onBind");
                bindService(mBindIntent, conn, Service.BIND_AUTO_CREATE);
                break;
            case R.id.un_bind:
                unbindService(conn);
                break;
            case R.id.button_intent_server_start:
                IntentServiceTest.startActionFoo(this,"param1","param2");
                break;
            default:
                Log.v(TAG, "nothing");
        }

    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG, "onServiceConnected");

            mSimpleBinder = (BindServiceTest.SimpleBinder) service;
            int addReturn = mSimpleBinder.add(1,3);
            Log.i(TAG, "ComponentName is: " + name.toString()
                    + " addReturn is " + addReturn);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "on onServiceDisconnected");
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(TAG,"on Activity onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.v(TAG,"on Activity onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG,"on Activity onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TAG,"on Activity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(TAG,"on Activity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG,"on Activity onDestroy");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
            Log.v(TAG,"screen change to ORIENTATION_LANDSCAPE");
        else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
            Log.v(TAG,"screen change to ORIENTATION_PORTRAIT");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.service_activity, menu);
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

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Log.v(TAG,"on Activity onPrepareOptionsMenu");
        return super.onPrepareOptionsMenu(menu);
    }
}