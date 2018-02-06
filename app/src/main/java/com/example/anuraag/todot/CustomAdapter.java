package com.example.anuraag.todot;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by anuraag on 5/2/18.
 */

public class CustomAdapter extends ArrayAdapter {

    Context context;
    ArrayList imageId,titles,event;

    @SuppressLint("ResourceType")
    public CustomAdapter(Context context, ArrayList imageId, ArrayList titles, ArrayList event) {
        super(context,R.id.listview,imageId);
        this.context = context;
        this.imageId = imageId;
        this.titles = titles;
        this.event = event;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rawView=inflater.inflate(R.layout.listview,null);

        ImageView imageView= (ImageView) rawView.findViewById(R.id.image);
        TextView txtTitle= (TextView) rawView.findViewById(R.id.showTitle);
        TextView txtEvent = rawView.findViewById(R.id.eventtime);

        imageView.setImageResource((Integer) imageId.get(position));
        txtTitle.setText(titles.get(position).toString());
        txtEvent.setText(event.get(position).toString());


        return rawView;
    }


}
