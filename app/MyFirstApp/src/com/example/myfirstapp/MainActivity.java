package com.example.myfirstapp;

import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	final private int menuSettings=Menu.FIRST;  
	final private int MenuApn=Menu.FIRST+1;
	private static final int REQ_SYSTEM_SETTINGS = 0;  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	/** Called when the user clicks the Send button */
	public void sendMessage(View view) {
	    Intent intent = new Intent(this, DisplayMessageActivity.class);
	    EditText editText = (EditText) findViewById(R.id.edit_message);
	    String message = editText.getText().toString();
	    intent.putExtra(EXTRA_MESSAGE, message);
	    startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		/*调用Activity的getMenuInflater()得到一个MenuInflater， 
	       使用inflate方法来把布局文件中的定义的菜单 加载给 第二个参数所对应的menu对象
	       在res目录下建立一个menu文件夹，并创建布局文件: options_menu.xml */
	//	getMenuInflater().inflate(R.menu.activity_main, menu);
		menu.add(0, menuSettings, 1, "设置");
		menu.add(0,MenuApn,0,"APN");
	    return super.onCreateOptionsMenu(menu); 
	}
	
    public boolean onMenuItemSelected(int featureId, MenuItem item)  
    {  
        switch (item.getItemId())  
        {  
            case menuSettings:  
                //转到Settings设置界面  
            	/*   1、组别，如果不分组的话就写Menu.NONE, 
            	      2、Id，这个很重要，Android根据这个Id来确定不同的菜单 
            	      3、顺序，哪个菜单项在前面由这个参数的大小决定 
            	      4、文本，菜单项的显示文本*/
                startActivityForResult(new Intent(this, Setting.class), REQ_SYSTEM_SETTINGS);  
                break;  
            case MenuApn:
            	Log.v("at menuApn","hellooooo");
     //      	Intent intent = new Intent(Intent.ACTION_INSERT,Uri.parse("content://telephony/carriers"));
            	Intent intent = new Intent(Intent.ACTION_INSERT);
            	intent.putExtra("apn_call", 7009);
            	startActivity(intent);
            default:  
                break;  
        }  
        return super.onMenuItemSelected(featureId, item);  
    }  
    //Settings设置界面返回的结果  
    protected  void onActivityResult(int requestCode, int resultCode, Intent data) {  
        if(requestCode == REQ_SYSTEM_SETTINGS)  
        {  
            //获取设置界面PreferenceActivity中各个Preference的值  
            String updateSwitchKey = getResources().getString(R.string.auto_update_switch_key);  
            String updateFrequencyKey = getResources().getString(R.string.auto_update_frequency_key);  
            //取得属于整个应用程序的SharedPreferences  
            SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);  
            Boolean updateSwitch = settings.getBoolean(updateSwitchKey, true);  
            String updateFrequency = settings.getString(updateFrequencyKey, "10");  
            //打印结果  
            Log.v("CheckBoxPreference_Main", updateSwitch.toString());  
            Log.v("ListPreference_Main", updateFrequency);  
        }  
        else  
        {  
            //其他Intent返回的结果  
        }
    }

}
