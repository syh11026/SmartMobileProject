package com.smartmobileproject.activity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.smartmobileproject.activity.R;
import com.smartmobileproject.function.JsonParsing;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static com.kakao.usermgmt.StringSet.email;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       //getPathOfAllImages();

       /* JsonParsing jsonParsing = new JsonParsing();
        try {
            jsonParsing.execute("https://phpproject-cparr.run.goorm.io/Shareuser.php?email="+email+"&shared_email"+shared_email).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

*/

    }

/*
    private ArrayList<String> getPathOfAllImages()
    {
        ArrayList<String> result = new ArrayList<>();
        Uri uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = { MediaStore.MediaColumns.DATA, MediaStore.MediaColumns.DISPLAY_NAME };

        Cursor cursor = getContentResolver().query(uri, projection, null, null, MediaStore.MediaColumns.DATE_ADDED + " desc");
        int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        int columnDisplayname = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME);

        int lastIndex;
        while (cursor.moveToNext())
        {
            String absolutePathOfImage = cursor.getString(columnIndex);
            String nameOfFile = cursor.getString(columnDisplayname);
            lastIndex = absolutePathOfImage.lastIndexOf(nameOfFile);
            lastIndex = lastIndex >= 0 ? lastIndex : nameOfFile.length() - 1;


            if (!TextUtils.isEmpty(absolutePathOfImage))
            {
                result.add(absolutePathOfImage);
            }
        }

        for (String string : result)
        {
            Log.i("PhotoSelectActivity.java | getPathOfAllImages", "|" + string + "|");
        }
        return result;
    }


 */
}
