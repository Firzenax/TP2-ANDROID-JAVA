package com.example.flickrapp;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;

import java.util.Vector;

public class MyAdapter extends BaseAdapter {

    private Vector<String> vector;

    public MyAdapter(){
        this.vector = new Vector<String>();
    }

    @Override
    public int getCount() {
        return vector.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.bitmaplayout, parent, false);
        }

        ImageView img = (ImageView)convertView.findViewById(R.id.imageView);

        Response.Listener<Bitmap> rep_listener = response -> {
            img.setImageBitmap(response);
        };

        // On crée une imageRequest contenant la position de l'url de l'image dans le vector et une création de bitmap avec les response listener
        ImageRequest request = new ImageRequest(vector.get(position), rep_listener,  0, 0, ImageView.ScaleType.CENTER_CROP, null, null);


        // On ajoute au singleton (qui est un genre de liste chainée) les information de l'image que l'oin cherche à charger.
        MySingleton.getInstance(parent.getContext()).addToRequestQueue(request);

        return convertView;
    }

    public void dd(String str){
        this.vector.add(str);
    }
}
