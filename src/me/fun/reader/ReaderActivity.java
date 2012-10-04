package me.fun.reader;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ReaderActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ListView listview = (ListView)findViewById(R.id.listView1);
    	
    	String[] list = new String[136];
    	initial(this, list);
    	
    	// First parameter - Context
    	// Second parameter - Layout for the row
    	// Third parameter - ID of the View to which the data is written
    	// Forth - the Array of data
    	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item, R.id.item, list);
    	listview.setAdapter(adapter);
    	
    	
    	// set event
    	listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    		@Override
    		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    			Intent it = new Intent(ReaderActivity.this, TextActivity.class);
    			it.putExtra("pos", position);
    			startActivity(it);
    		}
		});
    	
    }
    
    public void initial (Context ct, String[] list)
    {    	
    	AssetManager am = ct.getAssets();
    	
    	for (int k=0; k<136; k++){
    		try{
    			InputStream is = am.open("ch" + Integer.toString(k+1) + ".txt");
    			InputStreamReader isr = new InputStreamReader(is);
    			BufferedReader br = new BufferedReader(isr);
    			list[k] = br.readLine();
    			br.close();
    			isr.close();
    			is.close();
    		}
    		catch (Exception e){
    			e.printStackTrace();
    		}
    	}
    }
}