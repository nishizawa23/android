package com.helloworldcpp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class HelloWorldCPPActivity extends Activity {
	private static final String TAG = "hello world at android with JNI";
	static {
		System.loadLibrary("helloworld");
	}
	private native String pJNI();
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Log.d(TAG,"Activity call JNI:"+ pJNI());
    }
}