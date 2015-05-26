package com.andersdinesen.imagelistview;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ad on 22-05-2015.
 */
public class ImagePullServiceOld extends IntentService {
    public static final String BROADCAST_PULL_DONE= "com.andersdinesen.imagelistview.ImagePullServiceOld.PULL_DONE";

    public ImagePullServiceOld(){
        super("ImagePullServiceOld");
    }

    public ImagePullServiceOld(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent workIntent){
        String urlPath1 = "http://webcam.trafikken.dk/webcam/VejleN_Horsensvej_Cam1.jpg";
        Bitmap image = null;
        try {
            // Load file 1
            URL url = new URL(urlPath1);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream is = connection.getInputStream();
            image = BitmapFactory.decodeStream(is);

            String filename1 = "file2";
            //FileOutputStream outputStream = openFileOutput(filename1, Context.MODE_PRIVATE);
            File file = File.createTempFile(filename1, null, getBaseContext().getCacheDir());
            FileOutputStream outputStream = new FileOutputStream(file);
            image.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);

            Intent localIntent = new Intent(BROADCAST_PULL_DONE);
            localIntent.putExtra("filename", file.getAbsolutePath());
            LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
