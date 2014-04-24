package com.alpha.component.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by pete_doherty on 14-4-10.
 */
public class BroadCastTest extends BroadcastReceiver {

    private final static String TAG = "BroadCastTest";
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.v(TAG,"onReceive");
        if(intent.getAction() == "com.alpha.component.app.action") {
            String msg = intent.getStringExtra("MSG");
            Log.v(TAG, "Receive msg is: " + msg);
        }

    }
}
