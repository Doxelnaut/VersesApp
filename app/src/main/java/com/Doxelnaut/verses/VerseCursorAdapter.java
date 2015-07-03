package com.Doxelnaut.verses;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class VerseCursorAdapter extends CursorAdapter {


	public VerseCursorAdapter (Context context, Cursor c, int flags) {
		super(context, c, flags);
	}

	static class ViewHolder {
		public TextView title;
		public TextView version;
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		LayoutInflater inflater =  (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.listview_item,null,true);

		ViewHolder holder = new ViewHolder();
		holder.title= (TextView) rowView.findViewById(R.id.vTitle);
		holder.version= (TextView) rowView.findViewById(R.id.vVersion);
		rowView.setTag(holder);
		return rowView;
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		ViewHolder holder = (ViewHolder) view.getTag();

		String book = cursor.getString(DBAdapter.VerseCol.Book.ordinal());
		String version = cursor.getString(DBAdapter.VerseCol.Version.ordinal());
		String chapter = cursor.getString(DBAdapter.VerseCol.Chapter.ordinal());
		String verse = cursor.getString(DBAdapter.VerseCol.Verse.ordinal());
		
		holder.title.setText(book + " " + chapter + ":" + verse);    
		holder.version.setText(version);
	}

}
