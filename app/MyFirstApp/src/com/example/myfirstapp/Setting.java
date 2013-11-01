package com.example.myfirstapp;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.util.Log;
import android.view.Menu;

//继承PreferenceActivity，并实现OnPreferenceChangeListener和OnPreferenceClickListener监听接口
public class Setting extends PreferenceActivity implements OnPreferenceChangeListener, 
OnPreferenceClickListener,OnSharedPreferenceChangeListener {
	//定义相关变量
	final private int menuSettings=Menu.FIRST;  
	final private int MenuApn=Menu.FIRST+1;
	String updateSwitchKey;
	String updateFrequencyKey;
	CheckBoxPreference updateSwitchCheckPref;
	ListPreference updateFrequencyListPref;
  @SuppressWarnings("deprecation")
@Override
  public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      //从xml文件中添加Preference项
      addPreferencesFromResource(R.xml.preferencesii);
      //获取各个Preference
      updateSwitchKey = getResources().getString(R.string.auto_update_switch_key);
      updateFrequencyKey = getResources().getString(R.string.auto_update_frequency_key);
      updateSwitchCheckPref = (CheckBoxPreference)findPreference(updateSwitchKey);
      updateFrequencyListPref = (ListPreference)findPreference(updateFrequencyKey);
      //为各个Preference注册监听接口
      updateSwitchCheckPref.setOnPreferenceChangeListener(this);
      updateSwitchCheckPref.setOnPreferenceClickListener(this);
      updateFrequencyListPref.setOnPreferenceChangeListener(this);
      updateFrequencyListPref.setOnPreferenceClickListener(this);       
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
	@Override
	public boolean onPreferenceChange(Preference preference, Object newValue) {
		// TODO Auto-generated method stub
		Log.v("SystemSetting", "preference is changed");
		Log.v("Key_SystemSetting", preference.getKey());
		//判断是哪个Preference改变了
		if(preference.getKey().equals(updateSwitchKey))
		{
			Log.v("SystemSetting", "checkbox preference is changed");
		}
		else if(preference.getKey().equals(updateFrequencyKey))
		{
			Log.v("SystemSetting", "list preference is changed");
		}
		else
		{
			//如果返回false表示不允许被改变
			return false;
		}
		//返回true表示允许改变
		return true;
	}
	
	@Override
	public boolean onPreferenceClick(Preference preference) {
		// TODO Auto-generated method stub
		Log.v("SystemSetting", "preference is clicked");
		Log.v("Key_SystemSetting", preference.getKey());
		//判断是哪个Preference被点击了
		if(preference.getKey().equals(updateSwitchKey))
		{
			Log.v("SystemSetting", "checkbox preference is clicked");
		}
		else if(preference.getKey().equals(updateFrequencyKey))
		{
			Log.v("SystemSetting", "list preference is clicked");
		}
		else
		{
			return false;
		}
		return true;
	}
	@Override
	public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,	Preference preference) {
		String key = preference.getKey();
		Log.v("SystemSetting", "gggggggggggggggggggggg");
		if( key != null ){
			if(key.equals(updateSwitchKey)) {
				Log.v("SystemSetting", "onPreferenceTreeClick checkbox preference is clicked");
			}
			if(key.equals(updateFrequencyKey)) {
				Log.v("SystemSetting", "onPreferenceTreeClick ist preference is clicked");
			}
		}
	//	return super.onPreferenceTreeClick(preferenceScreen, preference);
		return true;
	}
	//动态获取 CheckBoxPreerence 的值
    @Override  
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {  
        if (key.equals(updateSwitchKey)) {  
            boolean value = sharedPreferences.getBoolean(key, false);  
            Log.d("mark", "onSharedPreferenceChanged is invoked !" + " and the value = " + value);  
        }  
    }  
}