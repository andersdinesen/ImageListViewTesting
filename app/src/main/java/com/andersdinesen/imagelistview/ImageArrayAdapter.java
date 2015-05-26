package com.andersdinesen.imagelistview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;

/**
 * Created by ad on 20-05-2015.
 */
public class ImageArrayAdapter extends ArrayAdapter<HashMap<String, Object>> {
    private Context context;
    private List<HashMap<String, Object>> values;

    public ImageArrayAdapter(Context context, List<HashMap<String, Object>> values) {
        super(context, R.layout.image_rowlayout, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.image_rowlayout, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.label);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.image);

        textView.setText((String) values.get(position).get("Name"));
        imageView.setImageResource(((Integer) values.get(position).get("ImageId")).intValue());

        String urlPath = "http://webcam.trafikken.dk/webcam/VejleN_Horsensvej_Cam1.jpg";
        Bitmap image = null;
        try {
            URL url = new URL(urlPath);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream is = connection.getInputStream();
            image = BitmapFactory.decodeStream(is);

        } catch (IOException e) {
            e.printStackTrace();
        }
/*
        if(image != null) {
            imageView.setImageBitmap(image);
        }
*/
        return rowView;
    }
}
