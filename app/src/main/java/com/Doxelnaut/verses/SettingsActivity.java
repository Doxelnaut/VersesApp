package com.Doxelnaut.verses;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

/**
 * Created by Corey on 7/3/2015.
 */
public class SettingsActivity extends AppCompatActivity {

    Toolbar _toolbar;
    Menu _menu;
    SettingsMenuFragment _frag;
//    AppearanceFragment _appearance;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //setup actionbar
        _toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(_toolbar);
        _toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        _frag = new SettingsMenuFragment();
//        _appearance = new AppearanceFragment();

        //add settingsMenu Fragment
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.settingsMenuFragment, _frag);
        transaction.commit();

    }
//--------------------------------------------------------------------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings_actionmenu, menu);
        _menu = menu;
        _toolbar.setTitle("Settings");
        _toolbar.setNavigationIcon(R.drawable.ic_action_previous_item);
        return super.onCreateOptionsMenu(menu);
    }
    //----------------------------------------------------------------------------------------------
    public void openAppearance(){
        //add appearance Fragment
//        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//        transaction.replace(R.id.settingsMenuFragment, _appearance);
//        transaction.commit();
    }
}
