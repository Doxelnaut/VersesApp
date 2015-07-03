package com.Doxelnaut.verses;

import android.app.Fragment;
import android.app.FragmentManager;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class VerseFragment extends Fragment {
	
	DBAdapter _db;
	Verse verse;
	
	public void onCreate(){
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
		//set View for the Fragment
		return inflater.inflate(R.layout.verse_fragment, container, false);
		
	}
	
	
	
	@Override
	public void onStart() {
	    super.onStart();
		_db = new DBAdapter(getActivity());		
		Bundle args = this.getArguments();
		setVerse(args.getInt("verseId"));
	}

	
	
	public void setVerse(int i){
		
		_db.open();
		Cursor c = _db.getVerse(i);	
		c.moveToFirst();
		
		//setVerse Id
		int id = c.getInt(DBAdapter.VerseCol.RowId.ordinal());
		verse = new Verse(id);
		
		//set book name
		String book = c.getString(DBAdapter.VerseCol.Book.ordinal());
		verse.set_Book(book);
		TextView bookView = (TextView)getView().findViewById(R.id.BookName);
		bookView.setText(book);

		//set VerseNumber
		String verseNum = c.getString(DBAdapter.VerseCol.Verse.ordinal());
		verse.set_Verse(verseNum);
		TextView verseNumView = (TextView)getView().findViewById(R.id.VerseNumber);
		verseNumView.setText(verseNum);
		
		//set chapter number
		int chapter = c.getInt(DBAdapter.VerseCol.Chapter.ordinal());
		verse.set_Chapter(chapter);
		TextView chapterNum = (TextView)getView().findViewById(R.id.Chapter);
		chapterNum.setText(String.valueOf(chapter));
		
		//set Verse Text
		String verseText = c.getString(DBAdapter.VerseCol.Text.ordinal());
		verse.set_Text(verseText);
		TextView verseTextView = (TextView)getView().findViewById(R.id.Verse);
		verseTextView.setText(verseText);	
		
		//set Verse Version
		String version = c.getString(DBAdapter.VerseCol.Version.ordinal());
		verse.set_Version(version);
		
		//set Verse Rank
		int rank = c.getInt(DBAdapter.VerseCol.Rank.ordinal());
		verse.set_Rank(rank);
		
		//set Verse Favorited
		int favorited = c.getInt(DBAdapter.VerseCol.Favorited.ordinal());
		verse.set_Favorited(favorited);
		
		//set Verse FavoriteRank
		int favRank = c.getInt(DBAdapter.VerseCol.FavRank.ordinal());
		verse.set_FavRank(favRank);
		
		_db.close();
	}
	

}
