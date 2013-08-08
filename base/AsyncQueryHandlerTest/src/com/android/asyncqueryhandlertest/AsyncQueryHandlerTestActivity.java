package com.android.asyncqueryhandlertest;

import android.app.Activity;
import java.util.ArrayList;
import java.util.List;
import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class AsyncQueryHandlerTestActivity extends Activity {

	private static final String NAME = "name", NUMBER = "number", SORT_KEY = "sort_key";
	    
	private List<ContentValues> listData;
        private AsyncQueryHandler asyncQuery;  
	    
	private ListView personList;
    	private BaseAdapter adapter; 

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

	personList = (ListView) findViewById(R.id.list_view);      
        asyncQuery = new MyAsyncQueryHandler(getContentResolver());

        asyncQueryContact();  
    }

    private void asyncQueryContact() {
        Uri uri = Uri.parse("content://com.android.contacts/data/phones");  
        String[] projection = { "_id", "display_name", "data1", "sort_key" };  
        asyncQuery.startQuery(0, null, uri, projection, null, null,"sort_key COLLATE LOCALIZED asc");
	}


	private class MyAsyncQueryHandler extends AsyncQueryHandler {  
  
        public MyAsyncQueryHandler(ContentResolver cr) {  
            super(cr);  
  
        }  
  
        @Override  
        protected void onQueryComplete(int token, Object cookie, Cursor cursor) {  
            if (cursor != null && cursor.getCount() > 0) {  
            	listData = new ArrayList<ContentValues>();  
                //cursor.moveToFirst();  
                for (int i = 0; i < cursor.getCount(); i++) {
                    ContentValues cv = new ContentValues();  
                    cursor.moveToPosition(i);  
                    String name = cursor.getString(1);  
                    String number = cursor.getString(2);  
                    String sortKey = cursor.getString(3);
                    if (number.startsWith("+86")) {  
                        cv.put(NAME, name);  
                        //process (+86);
                        cv.put(NUMBER, number.substring(3));  
                        cv.put(SORT_KEY, sortKey);  
                    } else {  
                        cv.put(NAME, name);  
                        cv.put(NUMBER, number);  
                        cv.put(SORT_KEY, sortKey);  
                    }  
                    listData.add(cv);  
                }  
                if (listData.size() > 0) {  
                    setAdapter(listData);  
                }  
                cursor.close();
            }  
        }  
  
    }  
  
    
    private void setAdapter(List<ContentValues> listData) {
    	adapter = new ListAdapter(this, listData);
        personList.setAdapter(adapter);  
  
    }
    
    
    private class ListAdapter extends BaseAdapter {
    	
    	 private LayoutInflater inflater;  
         private List<ContentValues> list;
    	
    	public ListAdapter(Context context, List<ContentValues> list) {
    		this.inflater = LayoutInflater.from(context);
    		this.list = list;   		
    	}
    	
		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {  
                convertView = inflater.inflate(R.layout.list_item, null);
                holder = new ViewHolder();  
                holder.name = (TextView) convertView.findViewById(R.id.name);  
                holder.number = (TextView) convertView.findViewById(R.id.number);  
                convertView.setTag(holder);  
            } else {  
                holder = (ViewHolder) convertView.getTag();  
            }  
			
            ContentValues cv = list.get(position);  
            holder.name.setText(cv.getAsString(NAME));
            holder.number.setText(cv.getAsString(NUMBER));

            return convertView;  
		}
		
		private class ViewHolder { 
            TextView name;  
            TextView number;
		}
    	
    }
    
}
