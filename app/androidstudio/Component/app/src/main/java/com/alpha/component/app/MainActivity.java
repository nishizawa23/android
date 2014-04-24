package com.alpha.component.app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.alpha.component.activitytest.ActivityTest;
import com.alpha.component.notification.NotificationTest;
import com.alpha.component.provider.ProviderActivity;
import com.alpha.component.service.ServiceActivity;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    private static final String TAG = "MainActivity";
    private static final String BROADCAST_ACTION =  "com.alpha.component.app.action";
    private static final String BROADCAST_EXTRA =  "MSG";
    private Button serviceButton;
    private Button broadcastButton;
    private Button activityButton;
    private Button providerButton;
    private Button notificationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityButton = (Button)findViewById(R.id.button_test_activity);
        serviceButton = (Button)findViewById(R.id.button_test_service);
        broadcastButton = (Button)findViewById(R.id.button_test_broadcast);
        providerButton = (Button)findViewById(R.id.button_test_provider);
        notificationButton = (Button)findViewById(R.id.button_test_notification);

        activityButton.setOnClickListener(this);
        serviceButton.setOnClickListener(this);
        broadcastButton.setOnClickListener(this);
        providerButton.setOnClickListener(this);
        notificationButton.setOnClickListener(this);

        Log.v(TAG,"isTaskRoot: "+ this.isTaskRoot());

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.button_test_activity:
                Intent mIntentActivity = new Intent(this,ActivityTest.class);
                startActivity(mIntentActivity);
            case R.id.button_test_service:
                Intent mIntentService = new Intent(this,ServiceActivity.class);
                startActivity(mIntentService);
                break;
            case R.id.button_test_broadcast:
                Intent mIntentBroadcast = new Intent();
                mIntentBroadcast.setAction(BROADCAST_ACTION);
                mIntentBroadcast.putExtra(BROADCAST_EXTRA,"this send form main activity");
                sendBroadcast(mIntentBroadcast);
                break;
            case R.id.button_test_provider:
                Intent mIntentProvider = new Intent(this, ProviderActivity.class);
                startActivity(mIntentProvider);
                break;
            case R.id.button_test_notification:
                Intent mIntentNotification = new Intent(this, NotificationTest.class);
                startActivity(mIntentNotification);
                break;
            default:
                Log.v(TAG,"No ID for onClick");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
