package com.andersdinesen.imagelistview;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by ad on 20-05-2015.
 */
public class MyListActivity extends ListActivity{

    public void onCreate(Bundle svavedInstanceState){
        super.onCreate(svavedInstanceState);
        String[] values =  new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2" };

        ArrayAdapter<String> adapter = new MySimpleArrayAdapter(this, values);
        setListAdapter(adapter);
    }

    protected void onListItemClick(ListView listView, View view, int position, long id){
        String item = (String) getListAdapter().getItem(position);
        Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
    }
}
