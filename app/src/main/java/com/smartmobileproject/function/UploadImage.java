package com.smartmobileproject.function;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import androidx.loader.content.CursorLoader;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.SimpleMultiPartRequest;
import com.android.volley.toolbox.Volley;

public class UploadImage {

    private Context context;
    private String imgPath;

    public UploadImage(Context context){
        this.context = context;

    }
    String getRealPathFromUri(Uri uri){
        String[] proj= {MediaStore.Images.Media.DATA};
        CursorLoader loader= new CursorLoader(context, uri, proj, null, null, null);
        Cursor cursor= loader.loadInBackground();
        int column_index= cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result= cursor.getString(column_index);
        cursor.close();
        return  result;
    }

    public void Upload(){


        String serverUrl="https://phpproject-cparr.run.goorm.io/insetDB.php";

        SimpleMultiPartRequest smpr= new SimpleMultiPartRequest(Request.Method.POST, serverUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("UPLOAD","성공");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Upload","fail");
            }
        });

        Log.d("img",imgPath);
        smpr.addFile("img", imgPath);

        RequestQueue requestQueue= Volley.newRequestQueue(context);
        Log.d("smpr", String.valueOf(smpr));
        requestQueue.add(smpr);
    }

    }







