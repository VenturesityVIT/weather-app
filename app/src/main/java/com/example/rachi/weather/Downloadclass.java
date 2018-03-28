package com.example.rachi.weather;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by rachi on 18-03-2018.
 */

public class Downloadclass extends AsyncTask<String,Void,String> {


    @Override
    protected String doInBackground(String...urls) {
        String result="";
        URL url;
        HttpURLConnection urlConnection= null;
        try {
            url=new URL(urls[0]);
            urlConnection=(HttpURLConnection) url.openConnection();
            InputStream in=urlConnection.getInputStream();
            InputStreamReader reader=new InputStreamReader(in);
            int data=reader.read();
            while(data != -1)
            {
                char currnet=(char) data;
                result+=currnet;
                data= reader.read();
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        try {
            JSONObject jsonObject=new JSONObject(result);
            JSONObject weatherData=new JSONObject(jsonObject.getString("main"));
            double temperature=Double.parseDouble(weatherData.getString("temp"));
            String placename=jsonObject.getString("name");
            MainActivity.t.setText(String.valueOf(temperature));
            MainActivity.n.setText(String.valueOf(placename));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
