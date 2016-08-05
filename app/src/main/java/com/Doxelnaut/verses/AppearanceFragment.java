//package com.Doxelnaut.verses;
//
//import android.app.Activity;
//import android.app.ListFragment;
//import android.content.DialogInterface;
//import android.graphics.Color;
//import android.graphics.drawable.ColorDrawable;
//import android.os.Bundle;
//import android.support.annotation.IdRes;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ListView;
//import android.widget.Toolbar;
//
////import com.flask.colorpicker.ColorPickerView;
////import com.flask.colorpicker.OnColorSelectedListener;
////import com.flask.colorpicker.builder.ColorPickerClickListener;
////import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
//
///**
// * Created by Corey on 7/3/2015.
// */
//public class AppearanceFragment extends ListFragment {
//
//    String[] _items = {"Primary Color", "Secondary Color", "Menu Text", "Verse Text", "Icon Color"};
//    Activity _act;
//    int _primaryColor;
//    int _secondaryColor;
//    int _menuText;
//    int _iconColor;
//    int _verseText;
//    AppearanceListAdapter _adapter;
//    View _view;
//
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        _adapter = new AppearanceListAdapter(getActivity(),_items);
//        setListAdapter(_adapter);
//    }
//
//
//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        _act = getActivity();
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        _view = inflater.inflate(R.layout.appearance_fragment, null);
//        return _view;
//    }
//
//    @Override
//    public void onViewCreated(View view, Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        getListView().setDivider(new ColorDrawable(this.getResources().getColor(R.color.ColorPrimary)));
//        getListView().setDividerHeight(2);
//    }
//
//    @Override
//    public void onListItemClick(ListView l, View v, int position, long id) {
//        super.onListItemClick(l, v, position, id);
//        switch(position) {
//            case 0:
//                openColorPickerPrimary();
//                return;
//            case 1:
//                openColorPickerSecondary();
//                return;
//            case 2:
//                openColorPickerMenuText();
//                return;
//            case 3:
//                openColorPickerVerseText();
//                return;
//            case 4:
//                openColorPickerIcon();
//                return;
//        }
//    }
//
//    //set Secondary Color
//    private void openColorPickerSecondary(){
//        int[] colors = {Color.RED,Color.BLUE};
//        ColorPickerDialogBuilder
//                .with(_act)
//                .setTitle("Choose color")
//                .initialColors(colors)
//                .initialColor(R.color.ColorSecondary)
//                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
//                .density(12)
//                .setPositiveButton("ok", new ColorPickerClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
//                        _secondaryColor = selectedColor;
//                        _view.setBackground(new ColorDrawable(_secondaryColor));
//                    }
//                })
//                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                    }
//                })
//                .build()
//                .show();
//    }
//    //--------------------------------------------------------------------------------------------
//    //set PrimaryColor
//
//    private void openColorPickerPrimary(){
//        int[] colors = {Color.RED,Color.BLUE};
//        ColorPickerDialogBuilder
//                .with(_act)
//                .setTitle("Choose color")
//                .initialColors(colors)
//                .initialColor(R.color.ColorPrimary)
//                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
//                .density(12)
//                .setPositiveButton("ok", new ColorPickerClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
//                        _primaryColor = selectedColor;
//                        ((SettingsActivity)_act).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(_primaryColor));
//                    }
//                })
//                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                    }
//                })
//                .build()
//                .show();
//    }
////--------------------------------------------------------------------------------------------------
//
//    //set MenuText Color
//    private void openColorPickerMenuText(){
//        int[] colors = {Color.RED,Color.BLUE};
//        ColorPickerDialogBuilder
//                .with(_act)
//                .setTitle("Choose color")
//                .initialColors(colors)
//                .initialColor(R.color.ColorMenuText)
//                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
//                .density(12)
//                .setPositiveButton("ok", new ColorPickerClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
//                        _menuText = selectedColor;
//                    }
//                })
//                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                    }
//                })
//                .build()
//                .show();
//    }
//    //---------------------------------------------------------------------------------------------
//
//    //set VerseText Color
//    private void openColorPickerVerseText(){
//        int[] colors = {Color.RED,Color.BLUE};
//        ColorPickerDialogBuilder
//                .with(_act)
//                .setTitle("Choose color")
//                .initialColors(colors)
//                .initialColor(R.color.ColorVerseText)
//                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
//                .density(12)
//                .setPositiveButton("ok", new ColorPickerClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
//                        _verseText = selectedColor;
//                    }
//                })
//                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                    }
//                })
//                .build()
//                .show();
//    }
//    //----------------------------------------------------------------------------------------------
//
//    //set Icon Color
//    private void openColorPickerIcon(){
//        int[] colors = {Color.RED,Color.BLUE};
//        ColorPickerDialogBuilder
//                .with(_act)
//                .setTitle("Choose color")
//                .initialColors(colors)
//                .initialColor(R.color.ColorIcons)
//                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
//                .density(12)
//                .setPositiveButton("ok", new ColorPickerClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
//                        _iconColor = selectedColor;
//                    }
//                })
//                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                    }
//                })
//                .build()
//                .show();
//    }
//}
