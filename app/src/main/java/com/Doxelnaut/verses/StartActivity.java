package com.Doxelnaut.verses;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;


public class StartActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DBAdapter adapter = new DBAdapter(this);
		adapter.open();
		adapter.resetTables();
		Cursor c = adapter.getValFromPersist(new String[] {DBAdapter.KEY_NEW});
		c.moveToFirst();
		int _new = c.getInt(0);
		if(_new == 1){startActivity(new Intent(this, MainActivity.class));}//start welcome screen; CHANGE TO WELCOMEACTIVTY
		else{startActivity(new Intent(this, MainActivity.class));}	//start app
			
		
	}

}
