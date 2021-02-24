package com.example.flickrapp;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;

public class ListActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = (ListView)findViewById(R.id.list);

        MyAdapter myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);

        try{
            AsyncFlickrJSONDataForList task = new AsyncFlickrJSONDataForList("https://www.flickr.com/services/feeds/photos_public.gne?tags=trees&format=json", myAdapter);
            task.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

        RequestQueue queue = MySingleton.getInstance(this.getApplicationContext()).getRequestQueue();


    }
}