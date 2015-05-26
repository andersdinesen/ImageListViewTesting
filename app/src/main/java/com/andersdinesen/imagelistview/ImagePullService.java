package com.andersdinesen.imagelistview;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ad on 22-05-2015.
 */
public class ImagePullService extends IntentService {
    public static final String BROADCAST_PULL_DONE= "com.andersdinesen.imagelistview.ImagePullService.PULL_DONE";

    public ImagePullService(){
        super("ImagePullService");
    }

    public ImagePullService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent workIntent){
        String urlPath = "http://webcam.trafikken.dk/webcam/VejleN_Horsensvej_Cam1.jpg";
        Bitmap image = null;
        try {
            URL url = new URL(urlPath);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream is = connection.getInputStream();
            image = BitmapFactory.decodeStream(is);

            Intent locanIntent = new Intent(BROADCAST_PULL_DONE);
            LocalBroadcastManager.getInstance(this).sendBroadcast(locanIntent);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
