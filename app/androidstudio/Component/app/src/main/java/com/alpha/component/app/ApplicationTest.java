package com.alpha.component.app;

import android.app.Application;
import android.util.Log;

/**
 * Created by pete_doherty on 14-4-6.
 */
public class ApplicationTest extends Application {

    private static final String TAG = "ApplicationTest";

    @Override
    public void onCreate() {
        Log.v(TAG,"on ApplicationTest onCreate" );
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        Log.v(TAG,"on ApplicationTest onTerminate");
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        Log.v(TAG,"on ApplicationTest onLowMemory");
        super.onLowMemory();
    }
}
