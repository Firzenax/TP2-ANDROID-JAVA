package com.example.flickrapp;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class FlickrApp extends AppCompatActivity {

    private Button Getimage;
    private Button GoToList;
    private static ImageView img;
    public static Bitmap bm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flickr_app);

        img = (ImageView)findViewById(R.id.image);
        GoToList = (Button)findViewById(R.id.GoToList);
        Getimage = (Button)findViewById(R.id.Getimage);

        Getimage.setOnClickListener(new GetImageOnClickListener(){
            @Override
            public void onClick(View v) {
                super.onClick(v);
                setRes(bm); //la fonction setRes s'execute trop rapidement et charge donc la dernière
            }
        });

        GoToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listPage = new Intent(FlickrApp.this, ListActivity.class);
                startActivity(listPage);
            }
        });
    }

    public static void setRes(Bitmap bm){
        img.setImageBitmap(bm);
    }
}

