package com.example.lab2_android_java;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.AuthenticateButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView result = (TextView)findViewById(R.id.result);
                EditText LoginText = (EditText)findViewById(R.id.LoginEditText);
                EditText PasswordText = (EditText)findViewById(R.id.PasswordEditText);
                String Login = LoginText.getText().toString();
                String Password = PasswordText.getText().toString();
                MyThread myThread = new MyThread(Login,Password);
                myThread.start();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        result.setText("My result here.");
                    }
                });
            }
        });

    }
    private String readStream(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(is),1000);
        for (String line = r.readLine(); line != null; line =r.readLine()){
            sb.append(line);
        }
        is.close();
        return sb.toString();
    }
    public class MyThread extends Thread {

        private String Login;
        private String Password;

        public MyThread(String login, String password)
        {
            this.Login = login;
            this.Password = password;
        }

        public void run(){
            URL url = null;
            try {
                url = new URL("https://httpbin.org/basic-auth/bob/sympa");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                String basicAuth = "Basic " + Base64.encodeToString((Login + ":" + Password).getBytes(),
                        Base64.NO_WRAP);
                urlConnection.setRequestProperty ("Authorization", basicAuth);
                try {
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    String s = readStream(in);
                    Log.i("JFL", s);
                } finally {
                    urlConnection.disconnect();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}