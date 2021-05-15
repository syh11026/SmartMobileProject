package com.smartmobileproject.function;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JsonParsing extends AsyncTask<String, Void, String> {
    String Tag = "JsonParseTest";
    @Override
    protected String doInBackground(String... strings) {
        String url = strings[0];
        try {
            URL serverurl = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) serverurl.openConnection();
            httpURLConnection.connect();
            InputStream inputStream;
            int responsecode = httpURLConnection.getResponseCode();
            if(responsecode == HttpURLConnection.HTTP_OK){
                inputStream = httpURLConnection.getInputStream();
            }
            else{
                inputStream = httpURLConnection.getErrorStream();
            }
            InputStreamReader inputStreamReader  = new InputStreamReader(inputStream,"UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringbuilder = new StringBuilder();
            String line ;
            while((line = bufferedReader.readLine())!=null){
                stringbuilder.append(line);
            }
            bufferedReader.close();
            Log.d("Stringbuilder",stringbuilder.toString().trim());
            return stringbuilder.toString().trim();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

