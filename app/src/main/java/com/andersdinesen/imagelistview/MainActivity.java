package com.andersdinesen.imagelistview;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor>{

    SimpleCursorAdapter mAdapter;
    SimpleAdapter simpleAdapter;


    static final String[] PROJECTION = new String[] {ContactsContract.Data._ID, ContactsContract.Data.DISPLAY_NAME, ContactsContract.Data.PHONETIC_NAME};
    static final String SELECTION = "((" +
            ContactsContract.Data.DISPLAY_NAME + " NOTNULL) AND (" +
            ContactsContract.Data.DISPLAY_NAME + " != '' ))";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        ProgressBar progressBar = new ProgressBar(this);
        progressBar.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        progressBar.setIndeterminate(true);
        getListView().setEmptyView(progressBar);
*/
        //ViewGroup root = (ViewGroup) findViewById(R.id.listView);
        //root.addView(progressBar);
        ListView listView = (ListView) findViewById(R.id.listView);

        /*
        String[] fromColumns = {ContactsContract.Data.DISPLAY_NAME, ContactsContract.Data.PHONETIC_NAME};
        int[] toViews = {android.R.id.text1, android.R.id.text2};
        mAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, null, fromColumns, toViews, 0);
        setListAdapter(mAdapter);
        */
        String[] from = new String[]{"name1", "name2", "name3", "name4"};
        int[] to = new int[] { R.id.item1, R.id.item2, R.id.item3, R.id.item4 };

        HashMap<String, String> element1 = new HashMap<String, String>();
        element1.put("name1","1");
        element1.put("name2", "Bob Smith");
        element1.put("name3", "John Doe");
        element1.put("name4", "Mr. Jones");

        HashMap<String, String> element2 = new HashMap<String, String>();
        element2.put("name1", "2");
        element2.put("name2", "Yet another name");
        element2.put("name3", "Yes name");
        element2.put("name4", "No name");

        ArrayList<HashMap<String, String>> listElements = new ArrayList<HashMap<String, String>>();
        listElements.add(element1);
        listElements.add(element2);

        simpleAdapter = new SimpleAdapter(this, listElements, R.layout.grid_item, from, to);
        listView.setAdapter(simpleAdapter);
        //getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, ContactsContract.Data.CONTENT_URI, PROJECTION, SELECTION, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if(mAdapter != null) {
            mAdapter.swapCursor(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        if(mAdapter != null) {
            mAdapter.swapCursor(null);
        }
    }
   /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/

}
