package com.Doxelnaut.verses;

/*
 * DBAdapter: adapter class for use with Verses Application.
 */

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
	
	//enumerable tpyes for accessing verse columns out of a cursor
	enum VerseCol{RowId, Text, Version, Book, Chapter, Verse, FavRank, Favorited, Rank}
	
	//Column definitions for Verses_Table
	static final String KEY_ROWID = "_id"; //unique identifier
	static final String KEY_TEXT = "text";
	static final String KEY_VERSION = "version";
	static final String KEY_BOOK = "book";
	static final String KEY_CHAPTER = "chapter";
	static final String KEY_VERSE = "verse";
	static final String KEY_RANK = "rank";
	static final String KEY_FAV = "favorited";
	static final String KEY_FAVRANK = "favorite_rank";
	
	//Column definitions for Persist_Table
	static final String KEY_NEW = "new";
	static final String KEY_FASTTRACKED = "fast_tracked";
	static final String KEY_USEDATE = "Use_date";  //Current Date, used to check if 24 hours has passed since last use
	static final String KEY_CURRENTROW_ID = "current_id";
	static final String KEY_ALARM = "alarm";
	static final String KEY_RANDOM = "random";
	
	//Global variables
	static final String TAG = "DBAdapter";
	static final String DATABASE_NAME = "VersesDB.db";
	static final String DATABASE_VERSESTABLE = "Verses_Table";
	static final String DATABASE_PERSISTTABLE = "Persist_Table";
	static final int DATABASE_VERSION = 1;
	
	//string used when defining the database table
	static final String DATABASE_CREATEVERSESTABLE = "create table Verses_Table (_id integer primary key autoincrement,text text not null, "
			+ "version text not null, book text not null, chapter integer not null, verse text not null, rank integer not null, favorited integer not null, favorite_rank integer not null);\r\n";
			
	static final String DATABASE_CREATEPERSISTTABLE = "create table Persist_Table (_idP integer primary key autoincrement, random integer, new integer not null, fast_tracked integer not null, Use_date text not null, current_id integer, alarm integer);";
	
	//context sent from android activity
	final Context context;
	
	//global databaseHelper instance
	DatabaseHelper DBHelper;
	
	//global database instance
	SQLiteDatabase db;

	//constructor
	public DBAdapter(Context ctx){
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
		DBHelper.CreateDatabase();
	}
	
	public void open(){
		DBHelper.openDatabase();
	}
	
	public void close(){
		DBHelper.close();
	}
//------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------
	
/*
 * DatabaseHelper class: class that communicates with SQL database.
 */
	private class DatabaseHelper extends SQLiteOpenHelper{
		
		//constructor
		DatabaseHelper(Context context){
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		
		public void onCreate(SQLiteDatabase db){
			return;
		}
		
		//allows for upgrading the database
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS Persist_Table");
			onCreate(db);
		}
		 /**
	     * Check if the database already exist to avoid re-copying the file each time you open the application.
	     * @return true if it exists, false if it doesn't
	     */
	    private boolean checkDataBase(){
	 
	    	SQLiteDatabase checkDB = null;
	 
	    	try{
	    		String myPath = context.getDatabasePath(DATABASE_NAME).getAbsolutePath();
	    		checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
	 
	    	}catch(SQLiteException e){
	 
	    		//database does't exist yet.
	 
	    	}
	 
	    	if(checkDB != null){
	 
	    		checkDB.close();
	 
	    	}
	 
	    	return checkDB != null ? true : false;
	    }
//----------------------------------------------------------------------------------------
	    /**
	     * Copies your database from your local assets-folder to the just created empty database in the
	     * system folder, from where it can be accessed and handled.
	     * This is done by transfering bytestream.
	     * */
	    private void copyDataBase() throws IOException{
	 
	    	//Open your local db as the input stream
	    	InputStream myInput = context.getAssets().open(DATABASE_NAME);
	 
	    	// Path to the just created empty db
	    	String outFileName = context.getDatabasePath(DATABASE_NAME).getAbsolutePath();
	 
	    	//Open the empty db as the output stream
	    	OutputStream myOutput = new FileOutputStream(outFileName);
	 
	    	//transfer bytes from the inputfile to the outputfile
	    	byte[] buffer = new byte[1024];
	    	int length;
	    	while ((length = myInput.read(buffer))>0){
	    		myOutput.write(buffer, 0, length);
	    	}
	 
	    	//Close the streams
	    	myOutput.flush();
	    	myOutput.close();
	    	myInput.close();
	 
	    }
//-----------------------------------------------------------------------------------------
	
		
		//opens the database
		public void openDatabase() throws SQLException{
			String myPath = context.getDatabasePath(DATABASE_NAME).getAbsolutePath();
	    	db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
		}
		
		//closes the database
		public void close(){
			db.close();
			super.close();
		}
		
		public void CreateDatabase(){
			if(checkDataBase())	{
				return;
			}
			
	    	this.getReadableDatabase();
	    	 
	    	try {

				copyDataBase();

			} catch (IOException e) {

	    		throw new Error("Error copying database");

	    	}
		}
	}
//-----------------------------------------------------------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------------------------------------------------------	   

	
	//retrieve all verses in favorites ordered by date																					      
	public Cursor getAllFavorites(){
		return db.query(DATABASE_VERSESTABLE, new String[] {KEY_ROWID, KEY_TEXT, KEY_VERSION, KEY_BOOK, KEY_CHAPTER, KEY_VERSE, KEY_FAVRANK, KEY_FAV,KEY_RANK}, KEY_FAV + "=1", null, null, null, KEY_FAVRANK +" DESC");
	}
	
	//retrieve a particular Verse
	public Cursor getVerse(long rowId) throws SQLException{
		Cursor mCursor = 
				db.query(true, DATABASE_VERSESTABLE, new String[] {KEY_ROWID, KEY_TEXT, KEY_VERSION, KEY_BOOK, KEY_CHAPTER, KEY_VERSE, KEY_FAVRANK, KEY_FAV,KEY_RANK }, KEY_ROWID + "=" + rowId, null, null, null, null, null);
		return mCursor;
	}
	
	//update  New
	public boolean updateNew(int _new){
		ContentValues args = new ContentValues();
		args.put(KEY_NEW, _new);
		return db.update(DATABASE_PERSISTTABLE, args, null, null) > 0;
	}
	
	
	//update fastTracked
	public boolean updateFastTracked(int _fastTracked){
		ContentValues args = new ContentValues();
		args.put(KEY_FASTTRACKED, _fastTracked);
		return db.update(DATABASE_PERSISTTABLE, args, null, null) > 0;
	}
	
	//update Alarm
		public boolean updateAlarm(int _alarm){
			ContentValues args = new ContentValues();
			args.put(KEY_ALARM, _alarm);
			return db.update(DATABASE_PERSISTTABLE, args, null, null) > 0;
		}
		
	//update UseDate and CurrentId
	public boolean updateDateAndCurrentId(int _currentId, String _date){
		ContentValues args = new ContentValues();
		args.put(KEY_USEDATE, _date);
		args.put(KEY_CURRENTROW_ID, _currentId);
		return db.update(DATABASE_PERSISTTABLE, args, null, null) > 0;
	}
	
	//Get Value From Persist
	public Cursor getValFromPersist(String[] keys){
		return db.query(DATABASE_PERSISTTABLE, keys, null, null, null, null, null, null);
	}
	
	//Get RowCount of Verses
	public int getCountofVerses(){
		int count;
		Cursor c = db.rawQuery("SELECT * FROM " + DATABASE_VERSESTABLE, null);
		count = c.getCount();
		return count;
	}
	
	
	public void resetTables(){
		db.delete(DATABASE_PERSISTTABLE, "1=1", null);		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
		
		//init Persist Table
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_NEW, 1);
		initialValues.put(KEY_RANDOM, 0);
		initialValues.put(KEY_FASTTRACKED, 0);
		initialValues.put(KEY_USEDATE, sdf.format(new Date()));
		initialValues.put(KEY_CURRENTROW_ID, 1);
		initialValues.put(KEY_ALARM, 0);
		db.insert(DATABASE_PERSISTTABLE, null,initialValues);
	}

}
