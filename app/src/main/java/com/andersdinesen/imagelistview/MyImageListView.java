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


public class MyImageListView extends ListActivity {
    ArrayAdapter<HashMap<String, Object>> adapter = null;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        ArrayList<String> names = new ArrayList<String>(Arrays.asList(new String[] { "E20 Lilleb\u00E6ldt", "E20 Kauslunde V"}));
        ArrayList<Integer> imageIds = new ArrayList<Integer>(Arrays.asList(new Integer[] {R.drawable.e20lillebaeldt, R.drawable.e20kauslundev}));
        ArrayList<HashMap<String, Object>> values = new ArrayList<HashMap<String, Object>>();
        for(int i = 0; i<names.size(); i++){
            HashMap<String, Object> element = new HashMap<String, Object>();
            element.put("Name", names.get(i));
            element.put("ImageId", R.drawable.spinningwheel);
            values.add(element);
        }
        getActionBar().setDisplayShowTitleEnabled(false);

        adapter = new ImageArrayAdapter(this, values);
        setListAdapter(adapter);

        // Start the service to pull new images
        Intent pullIntent = new Intent(this, ImagePullService.class);
        startService(pullIntent);

        // Register a receiver for when the pulling is done
        PullResponseReceiver pullResponseReveiver = new PullResponseReceiver();
        IntentFilter intentFilter = new IntentFilter(ImagePullService.BROADCAST_PULL_DONE);
        LocalBroadcastManager.getInstance(this).registerReceiver(pullResponseReveiver, intentFilter);
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
            MyImageListView.this.adapter.notifyDataSetChanged();
        }
    }
}
