package com.android.contentprovider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.test.AndroidTestCase;
import android.util.Log;

/**
 * 外部访问内容提供者
 * 
 * @author peter
 * @JUNIT会按字母顺序自动执行函数名以test开头的函数，只能测试public类和接口
 */
public class TestPerson extends AndroidTestCase{
    private static final String TAG = "PersonContentProviderTest";
    
    @Override
    protected void setUp() throws Exception {
        /*初始化你在测试中需要的一些动作及全局变量*/
        Log.i(TAG,"at setUp");
    }
    
    public void testBSave() throws Throwable{
        ContentResolver contentResolver = this.getContext().getContentResolver();
        Uri insertUri = Uri.parse("content://com.ljq.provider.personprovider/person");
        ContentValues values = new ContentValues();
        values.put("name", "ljq");
        values.put("phone", "1350000009");
        Uri uri = contentResolver.insert(insertUri, values);
        Log.i(TAG, uri.toString());
    }
    
    public void testCUpdate() throws Throwable{
        ContentResolver contentResolver = this.getContext().getContentResolver();
        Uri updateUri = Uri.parse("content://com.ljq.provider.personprovider/person/4");
        ContentValues values = new ContentValues();
        values.put("name", "linjiqin");
        contentResolver.update(updateUri, values, null, null);
    }
 
    public void testDFind() throws Throwable{
        ContentResolver contentResolver = this.getContext().getContentResolver();
        Uri uri = Uri.parse("content://com.ljq.provider.personprovider/person");
        Cursor cursor = contentResolver.query(uri, null, null, null, "id desc");
        while(cursor.moveToNext()){
            int personid = cursor.getInt(cursor.getColumnIndex("ID"));
            String name = cursor.getString(cursor.getColumnIndex("NAME"));
            String phone = cursor.getString(cursor.getColumnIndex("PHONE"));
            Log.i(TAG, "personid="+ personid + ",name="+ name+ ",phone="+ phone);
        }
        cursor.close();
    }
    
   public void testEDelete() throws Throwable{
        ContentResolver contentResolver = this.getContext().getContentResolver();
        Uri uri = Uri.parse("content://com.ljq.provider.personprovider/person/3");
        contentResolver.delete(uri, null, null);
    }
    
    @Override
    protected void tearDown() throws Exception{
        Log.i(TAG,"at tearDown");
        super.tearDown();
    }
    
    @Override
    public void testAndroidTestCaseSetupProperly(){
        super.testAndroidTestCaseSetupProperly();
        Log.i(TAG,"at testAndroidTestCaseSetupProperly()");
    }
}
