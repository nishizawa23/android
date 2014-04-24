package com.alpha.component.notification;

import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by pete_doherty on 14-4-22.
 */
public class NotificationShow extends Activity {

    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = NotificationShow.this.getIntent();
    }
}
