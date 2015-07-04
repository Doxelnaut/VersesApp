package com.Doxelnaut.verses;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Corey on 7/3/2015.
 */
public class SettingsMenuAdapter extends ArrayAdapter<String> {

    String[] _items;
    Context _context;

    static class ViewHolder {
        public TextView title;
    }

    public SettingsMenuAdapter(Context c, String[] objects){
        super(c,R.layout.setting_menu_listitem,objects);
        _context = c;
        _items = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View rowView = inflater.inflate(R.layout.setting_menu_listitem,null,true);
        ViewHolder holder = new ViewHolder();
        holder.title= (TextView) rowView.findViewById(R.id.sTitle);
        rowView.setTag(holder);
        holder.title.setText(_items[position]);
        return rowView;
    }
}
