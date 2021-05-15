package com.smartmobileproject.function;

import android.app.DownloadManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class downloadservice extends AppCompatActivity {
    DownloadManager downloadManager;
    private long enqueue;


    public void downloadimagefile(String url , String filename){
        downloadManager = (DownloadManager)getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setTitle(filename);
        request.setDescription("다운로드 중..");
        request.setNotificationVisibility(1);
        enqueue = downloadManager.enqueue(request);
    }

    public String requestdownloadurl(String requestfile, String request_url){


        String request = requestfile + request_url;

        JsonParsing json = new JsonParsing();

        AsyncTask<String, Void, String> result = json.execute(request);

        Log.d("result", String.valueOf(result));
        return null;

    }



}
