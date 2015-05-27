package com.andersdinesen.imagelistview;

import android.app.ListActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;


public class MyImageListView extends ListActivity {
    public static final String ELEMENTS_LIST_KEY = "ELEMENTS_LIST";
    ArrayAdapter<ElementInfo> adapter = null;
    ArrayAdapter<HashMap<String, Object>> adapterOld = null;
    ArrayList<ElementInfo> values = null;
    ArrayList<HashMap<String, Object>> valuesOld = null;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setupAdapter();
        getActionBar().setDisplayShowTitleEnabled(false);
        //setupAdapterOld();

        // Register a receiver for when the pulling is done
        PullResponseReceiver pullResponseReveiver = new PullResponseReceiver();
        IntentFilter intentFilter = new IntentFilter(ImagePullService.BROADCAST_PULL_DONE);
        LocalBroadcastManager.getInstance(this).registerReceiver(pullResponseReveiver, intentFilter);

        // Start the service to pull new images
        Intent pullIntent = new Intent(this, ImagePullService.class);
        pullIntent.putExtra(ELEMENTS_LIST_KEY, values);
        startService(pullIntent);
    }

    private void setupAdapter(){
        values = new ArrayList<>();
        ElementInfo element1 = new ElementInfo("E20 Lilleb\u00E6ldt", "VejleN_Horsensvej_Cam1.jpg", null);
        ElementInfo element2 = new ElementInfo("E20 Kauslunde V", "kauslunde2.jpg", null);

        values.add(element1);
        values.add(element2);

        adapter = new ImageArrayAdapter(this, values);
        setListAdapter(adapter);
    }

    private void setupAdapterOld() {
        ArrayList<String> names = new ArrayList<String>(Arrays.asList(new String[]{"E20 Lilleb\u00E6ldt", "E20 Kauslunde V"}));
        ArrayList<Integer> imageIds = new ArrayList<Integer>(Arrays.asList(new Integer[] {R.drawable.e20lillebaeldt, R.drawable.e20kauslundev}));
        valuesOld = new ArrayList<HashMap<String, Object>>();
        for(int i = 0; i<names.size(); i++){
            HashMap<String, Object> element = new HashMap<String, Object>();
            element.put("Name", names.get(i));
            element.put("ImageId", R.drawable.spinningwheel);
            valuesOld.add(element);
        }

        adapterOld = new ImageArrayAdapterOld(this, valuesOld);
        setListAdapter(adapterOld);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_image_list_view_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private class PullResponseReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            values.clear();

            ArrayList<ElementInfo> elementInfos = intent.getParcelableArrayListExtra(MyImageListView.ELEMENTS_LIST_KEY);
            ElementInfo elementInfo;
            for (Iterator<ElementInfo> elementInfoIterator = elementInfos.iterator(); elementInfoIterator.hasNext();) {
                elementInfo = elementInfoIterator.next();
                values.add(elementInfo);
            }
            //MyImageListView.this.adapter.notifyDataSetChanged();
            //values.clear();
            /*
            HashMap<String, Object> element = new HashMap<String, Object>();
            element.put("Name", intent.getStringExtra("filename"));
            element.put("ImageId", R.drawable.spinningwheel);
            */
            //values.add(element);

            adapter.notifyDataSetChanged();
        }
    }
}
