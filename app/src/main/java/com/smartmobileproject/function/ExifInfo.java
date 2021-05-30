package com.smartmobileproject.function;

import android.media.ExifInterface;

public class ExifInfo {

    private String ExifDate(ExifInterface exif){
        String dateinfo = ExifInterface.TAG_DATETIME;
        return dateinfo;
    }

    private double Exiflatitude(ExifInterface exif){
        String latitude_S = ExifInterface.TAG_GPS_LATITUDE;
        double latitude = Double.parseDouble(latitude_S);
        return latitude;

    }

    private double Exiflongtitude(ExifInterface exif){
        String lontitude_S = ExifInterface.TAG_GPS_LONGITUDE;
        double longtitude = Double.parseDouble(lontitude_S);
        return longtitude;
    }
}
