package me.fun.reader;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.TextView;

public class TextActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.display);
	    // TODO Auto-generated method stub
	    
	    TextView tv = (TextView)findViewById(R.id.textView1);
	    tv.setText("");
	    
	    int pos = 0;
	    Bundle extra = getIntent().getExtras();
	    if (extra != null)
	    {
	    	pos = extra.getInt("pos");
	    }
	    
	    String inputline;
	    AssetManager am = this.getAssets();
	    try{
	    	InputStream is = am.open("ch" + Integer.toString(pos+1) + ".txt");
	    	InputStreamReader isr = new InputStreamReader(is);
	    	BufferedReader br = new BufferedReader(isr);
	    	while ((inputline = br.readLine()) != null)
	    	{
	    		tv.append(inputline);
	    		tv.append("\n");
	    	}
	    	br.close();
	    	isr.close();
	    	is.close();
	    }
	    catch (Exception e)
	    {
	    	e.printStackTrace();
	    }
	}

}
