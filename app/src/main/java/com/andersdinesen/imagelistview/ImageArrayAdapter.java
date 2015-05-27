package com.andersdinesen.imagelistview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by ad on 20-05-2015.
 */
public class ImageArrayAdapter extends ArrayAdapter<ElementInfo> {
    private Context context;
    private List<ElementInfo> values;

    public ImageArrayAdapter(Context context, List<ElementInfo> values) {
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

        textView.setText(values.get(position).getDisplayName());
        if(values.get(position).getBitmap() != null){
            imageView.setImageBitmap(values.get(position).getBitmap());
        }
        return rowView;
    }
}
