package com.Doxelnaut.verses;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity{

	DBAdapter _db;
	int _currentVerse = 1;
	VerseFragment dailyVerse;
	VerseFragment _secondVerse = null;
	private Menu _menu;
	DrawerLayout drawer;
	ActionBarDrawerToggle mDrawerToggle;
	ListView fav;
	private Toolbar toolbar;
	int _secondVerseVisible = 0;
	Cursor _favoriteCursor;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//setup DB Connection
		_db = new DBAdapter(this);

		//Update Persist table in DB
		updatePersist();

		//init Verse Fragment
		dailyVerse = new VerseFragment();
		
		Bundle verseBundle = new Bundle();
		verseBundle.putInt("verseId", _currentVerse);
		dailyVerse.setArguments(verseBundle);
		
		//setup actionbar
		toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);   
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	onBackPressed();
            }
        });
			
		//setup drawer
        initFavoritesDrawer();
        
        //add Daily Fragment
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.verseFragment, dailyVerse);
		transaction.commit();
	}
	//--------------------------------------------------------------------------------

	/*
	 * updatePersist: updates the persistent data in the DB with a new currentID and date if the date differs from last use.
	 */
	private void updatePersist(){

		_db.open();
		Cursor c = _db.getValFromPersist(new String[] {DBAdapter.KEY_USEDATE, DBAdapter.KEY_CURRENTROW_ID, DBAdapter.KEY_RANDOM});
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
		String date = sdf.format(new Date());
		c.moveToFirst();
		String date2 = c.getString(0);


		if(date2.compareTo(date) == 0){
			//the app has already been updated today
			_currentVerse = c.getInt(1);
			return;
		}

		//check if id is randomly selected
		if(c.getInt(2)== 1){
			Random r = new Random();
			//_currentVerse = r.nextInt() % _db.getCountofVerses();
		}
		else{
			//increment currentId and set the next verse
			//_currentVerse = c.getInt(1) + 1 ;
		}

		//update date and currentId in the database
		//_db.updateDateAndCurrentId(_currentVerse, date);		
	}

	public void onResume(){
		super.onResume();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		
		inflater.inflate(R.menu.options_menu, menu);
		_menu = menu;
		if(_secondVerseVisible == 1){
        toolbar.setNavigationIcon(R.drawable.ic_action_previous_item);
		}
		return super.onCreateOptionsMenu(menu);
	}
	
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = this.getFragmentManager();
		if(fragmentManager .getBackStackEntryCount() != 0) {
            fragmentManager.popBackStack();
            
            //change ActionBar back button
            if(_secondVerseVisible == 1){
        		getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            }
        } else {
            super.onBackPressed();
        }
    }

	//----------------------------------------------------------------------
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		  if (mDrawerToggle.onOptionsItemSelected(item)) {
	          return true;
		  }

		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.searchAction:
			//openSearch();
			return true;
		case R.id.favoritesAction:
			toggleDrawer();
			return true;
		case R.id.settingsAction:
			startActivity(new Intent(this, SettingsActivity.class));
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	//-------------------------------------------------------------------------
	@Override
	public void onNewIntent (Intent intent){
		//handleIntent(getIntent());
	}

	/*
	 * handleintent: handles the intent
	 */
	public void handleIntent(Intent intent){

	}
	//--------------------------------------------------------------------------------
	private void toggleDrawer(){
		if(drawer.isDrawerOpen(Gravity.RIGHT)){
			drawer.closeDrawer(Gravity.RIGHT);
		}
		else{
			drawer.openDrawer(Gravity.RIGHT);
		}
	}
	//-----------------------------------------------------------------------

	public void initFavoriteList(){
		fav = (ListView) findViewById(R.id.favoritesView);
		View header = getLayoutInflater().inflate(R.layout.header, fav, false);
		fav.addHeaderView(header);

		 _favoriteCursor = _db.getAllFavorites();
		VerseCursorAdapter _cAdapter = new VerseCursorAdapter(this,_favoriteCursor,0);
		fav.setAdapter(_cAdapter);
		fav.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				int verseId = _favoriteCursor.getInt(DBAdapter.VerseCol.RowId.ordinal());
				addFragment(verseId);
				toggleDrawer();
			}
	    });
	}
	//---------------------------------------------------------------------------
	
	private void initFavoritesDrawer(){
		drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);

		mDrawerToggle = new ActionBarDrawerToggle(this,drawer,R.string.openDrawer,R.string.closeDrawer){

			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				// code here will execute once the drawer is opened( As I dont want anything happened whe drawer is
				// open I am not going to put anything here)
				_menu.findItem(R.id.favoritesAction).setIcon(R.drawable.bookmark);
				
			}

			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
				// Code here will execute once drawer is closed
				_menu.findItem(R.id.favoritesAction).setIcon(R.drawable.bookmark_empty);
			}
		}; 

		// Drawer Toggle Object Made
		drawer.setDrawerListener(mDrawerToggle); // Drawer Listener set to the Drawer toggle
		mDrawerToggle.syncState(); 
		
		//setup favorites list
		initFavoriteList();
				
		//calculate width for drawer
	    int width = (int) ((int)getResources().getDisplayMetrics().widthPixels * 0.85);
	    
		//set width of the drawer
	    DrawerLayout.LayoutParams params = (android.support.v4.widget.DrawerLayout.LayoutParams) fav.getLayoutParams();
	    params.width = width;
	    fav.setLayoutParams(params);
	}
	
//---------------------------------------------------------------------------------------------------------------------
	/*
	 * Method addFragment: adds a new verseFragment to the activity
	 */
	
	public void addFragment(int i){
		_secondVerse = new VerseFragment();
		Bundle verseBundle = new Bundle();
		verseBundle.putInt("verseId", i);
		_secondVerse.setArguments(verseBundle);
		
		//remove old transaction
		FragmentManager fm = this.getFragmentManager();
		fm.popBackStack("fragB", FragmentManager.POP_BACK_STACK_INCLUSIVE);
	
        //add new Fragment
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.setCustomAnimations(R.anim.slide_right, R.anim.slide_right2, R.anim.slide_left, R.anim.slide_left2);
		transaction.replace(R.id.verseFragment, _secondVerse).addToBackStack("fragB");
		transaction.commit();
		
		_secondVerseVisible = 1;
		getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_previous_item);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}
}