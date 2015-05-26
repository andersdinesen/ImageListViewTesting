package com.andersdinesen.imagelistview;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.content.LocalBroadcastManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by ad on 22-05-2015.
 */
public class ImagePullService extends IntentService {
    public static final String BROADCAST_PULL_DONE= "com.andersdinesen.imagelistview.ImagePullServiceOld.PULL_DONE";

    public ImagePullService(){
        super("ImagePullServiceOld");
    }

    public ImagePullService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent workIntent){
        String urlPath = "http://webcam.trafikken.dk/webcam/";
        Bitmap image = null;
        try {
            ArrayList<ElementInfo> elementInfos = workIntent.getParcelableArrayListExtra(MyImageListView.ELEMENTS_LIST_KEY);

            ElementInfo elementInfo;
            for (Iterator<ElementInfo> elementInfoIterator = elementInfos.iterator(); elementInfoIterator.hasNext();){
                elementInfo = elementInfoIterator.next();
                URL url = new URL(urlPath + elementInfo.getRemoteFileName());
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream is = connection.getInputStream();
                image = BitmapFactory.decodeStream(is);
                elementInfo.setBitmap(image);
            }

            Intent localIntent = new Intent(BROADCAST_PULL_DONE);
            localIntent.putExtra(MyImageListView.ELEMENTS_LIST_KEY, elementInfos);
            //localIntent.putExtra("filename", file.getAbsolutePath());
            LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);

            // Load file 1
            /*
            URL url = new URL(urlPath);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream is = connection.getInputStream();
            image = BitmapFactory.decodeStream(is);
            */
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
