package com.smartmobileproject.function;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class PostResponse extends AsyncTask<String, Void, Void> {

@Override
protected Void doInBackground(String... params) {
        try {

        HashMap<String, String> map = new HashMap<>();
        map.put("id","test");

        URL url = new URL(params[0]);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        httpURLConnection.setRequestProperty("Accept-Charset","UTF-8");
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setConnectTimeout(10000);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setDoInput(false);

        StringBuffer buffer = new StringBuffer();

        for(String key : map.keySet()){
        buffer.append(key).append("=").append(map.get(key));
        }

        Log.d("test",buffer.toString());

        OutputStreamWriter outputStream = new OutputStreamWriter(httpURLConnection.getOutputStream());
        outputStream.write(buffer.toString());
        outputStream.flush();
        outputStream.close();
        if(httpURLConnection != null){
        httpURLConnection.setConnectTimeout(10000);


        if(httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
        Log.d("Log","성공");
        }

        }


        httpURLConnection.disconnect();
        } catch (MalformedURLException malformedURLException) {
        malformedURLException.printStackTrace();
        } catch (IOException ioException) {
        ioException.printStackTrace();
        }
        return null;
}
}