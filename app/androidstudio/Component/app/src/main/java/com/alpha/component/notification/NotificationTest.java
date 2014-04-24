package com.alpha.component.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.alpha.component.app.R;
import com.alpha.component.service.ServiceActivity;

public class NotificationTest extends ActionBarActivity {

    int notification_id=19172439;
    NotificationManager nm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_test);
        //showNotification();
        nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        showNotification(R.drawable.ic_launcher,"图标边的文字","标题","内容");
    }

    public void showNotification(int icon,String tickertext,String title,String content){
    //public void showNotification(){
        //设置一个唯一的ID，随便设置

        //Notification管理器
        Notification notification=new Notification(icon,tickertext,System.currentTimeMillis());

        //后面的参数分别是显示在顶部通知栏的小图标，小图标旁的文字（短暂显示，自动消失）系统当前时间（不明白这个有什么用）
        notification.defaults=Notification.DEFAULT_ALL;
        //这是设置通知是否同时播放声音或振动，声音为Notification.DEFAULT_SOUND
        //振动为Notification.DEFAULT_VIBRATE
        //Light为Notification.DEFAULT_LIGHTS
        //全部为Notification.DEFAULT_ALL
        //如果是振动或者全部，必须在AndroidManifest.xml加入振动权限
        //<uses-permission android:name="android.permission.VIBRATE" />
        // 	PendingIntent pt=PendingIntent.getActivity(this, 0, new Intent(this,notificationActivity.class), 0);
        PendingIntent pt=PendingIntent.getActivity(this, 0, new Intent(this,NotificationTest.class), 0);
        //点击通知后的动作，这里是转回main 这个Acticity
        notification.setLatestEventInfo(this,title,content,pt);
        nm.notify(notification_id, notification);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.notification_test, menu);
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
