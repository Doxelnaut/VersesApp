package com.Doxelnaut.verses;

import android.app.ListFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Corey on 7/3/2015.
 */
public class SettingsMenuFragment extends ListFragment {

    String[] _items = {"Verse Settings", "Appearance", "Notification Settings"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new SettingsMenuAdapter(getActivity(),_items));
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getListView().setDivider(new ColorDrawable(this.getResources().getColor(R.color.ColorPrimary)));
        getListView().setDividerHeight(2);
    }
}
