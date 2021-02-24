package com.example.flickrapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AsyncBitmapDownloader extends AsyncTask<String, Void, Bitmap> {

    private String httpUrl;
    private Bitmap bm;

    public AsyncBitmapDownloader(String str){
        this.httpUrl = str;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {

        URL url = null;

        try {
            url = new URL(this.httpUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            bm = BitmapFactory.decodeStream(in);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bm;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        FlickrApp.setRes(bm);

    }
}
