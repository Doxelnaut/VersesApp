package com.Doxelnaut.verses;

import android.app.Activity;
import android.app.ListFragment;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

/**
 * Created by Corey on 7/3/2015.
 */
public class AppearanceFragment extends ListFragment {

    String[] _items = {"Primary Color", "Secondary Color", "Menu Text", "Verse Text"};
    Activity _act;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new AppearanceListAdapter(getActivity(),_items));
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        _act = getActivity();
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getListView().setDivider(new ColorDrawable(this.getResources().getColor(R.color.ColorPrimary)));
        getListView().setDividerHeight(2);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        switch(position) {
            case 0:
                return;
            case 1:
                return;
        }
    }

}
